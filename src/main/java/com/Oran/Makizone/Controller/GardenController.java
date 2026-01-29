package com.Oran.Makizone.Controller;

import com.Oran.Makizone.DTO.GardenRequest;
import com.Oran.Makizone.DTO.GardenResponse;
import com.Oran.Makizone.Database.HibernateJPA.Model.Game.Garden;
import com.Oran.Makizone.Database.HibernateJPA.Model.Trade.User;
import com.Oran.Makizone.Database.HibernateJPA.Service.Catalog.ProductService;
import com.Oran.Makizone.Database.HibernateJPA.Service.Game.GardenService;
import com.Oran.Makizone.Database.HibernateJPA.Service.Trade.UserService;
import com.Oran.Makizone.Utilities.Enums.GardenTheme;
import com.Oran.Makizone.Utilities.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/garden")
public class GardenController {
    private final TokenService tokenService;
    private final UserService userService;
    private final GardenService gardenService;
    private final ProductService productService;
    private final com.Oran.Makizone.Database.HibernateJPA.Service.Game.PlayerProfileService playerProfileService;

    @Autowired
    public GardenController(UserService userService, TokenService tokenService, GardenService gardenService,
            ProductService productService,
            com.Oran.Makizone.Database.HibernateJPA.Service.Game.PlayerProfileService playerProfileService) {
        this.userService = userService;
        this.tokenService = tokenService;
        this.gardenService = gardenService;
        this.productService = productService;
        this.playerProfileService = playerProfileService;
    }

    @GetMapping
    public ResponseEntity<GardenResponse> getMyGarden(@RequestHeader("Authorization") String token) {
        String email = tokenService.extractMail(token);
        User user = userService.findUser(email);
        Garden garden = gardenService.findGarden(user);
        GardenTheme theme = garden.getTheme();
        Map<String, Object> layoutData = garden.getLayoutData();
        return ResponseEntity.ok(new GardenResponse(theme, layoutData));
    }

    @PostMapping("/action")
    public ResponseEntity<GardenResponse> updateGarden(
            @RequestHeader("Authorization") String token,
            @RequestBody GardenRequest gardenRequest) {

        // 1. Authentication & Data Retrieval
        String email = tokenService.extractMail(token);
        User user = userService.findUser(email);
        Garden garden = gardenService.findGarden(user);

        // 2. Extract request data
        int rowIndex = gardenRequest.getRowIndex();
        int colIndex = gardenRequest.getColIndex();
        GardenTheme theme = gardenRequest.getTheme();
        Map<String, Object> actionItem = gardenRequest.getActionItem();

        Map.Entry<String, Object> entry = actionItem.entrySet().iterator().next();
        String action = entry.getKey();
        String item = (String) entry.getValue();

        // 3. Update garden layout
        Map<String, Object> layoutData = garden.getLayoutData();
        updateGardenLayout(user, layoutData, rowIndex, colIndex, action, item);

        // 4. Save changes
        garden.setTheme(theme);
        garden.setLayoutData(layoutData);
        gardenService.updateGarden(garden);

        return ResponseEntity.ok(new GardenResponse(theme, layoutData));
    }

    // Helper method to handle layout logic
    private void updateGardenLayout(
            User user,
            Map<String, Object> layoutData,
            int rowIndex,
            int colIndex,
            String action,
            String item) {

        List<List<Integer>> grid = (List<List<Integer>>) layoutData.get("grid");
        Map<String, String> items = (Map<String, String>) layoutData.get("items");

        // Items map stores: "ProductCategoryID" -> "ProductName"
        // Grid stores: Integer (ProductCategoryID) or 0

        // Handle Harvest/Clear
        int currentItemNo = grid.get(rowIndex).get(colIndex);
        if (currentItemNo != 0) {
            // HARVEST Logic
            items.remove(String.valueOf(currentItemNo));

            // Allow harvesting implies gaining stats
            // Arbitrary rewards: +5 Health, +2 Fame
            playerProfileService.addStats(user, 5, 2);
        }
        grid.get(rowIndex).set(colIndex, 0);

        // Plant new item if action is PLANT
        if ("PLANT".equals(action)) {
            String itemNo = productService.categoryNumber(item);

            // CHECK INVENTORY
            if (!playerProfileService.removeItemFromInventory(user, itemNo, 1)) {
                throw new RuntimeException("Not enough seeds to plant " + item);
            }

            grid.get(rowIndex).set(colIndex, Integer.valueOf(itemNo));
            items.put(itemNo, item);
        }

        // Update layout data
        layoutData.put("grid", grid);
        layoutData.put("items", items);
    }
}
