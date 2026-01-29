<script setup>
import { ref, onMounted, computed } from 'vue'
import api from '../services/api' // Adjust path to your api.js

const loading = ref(true)
const garden = ref({
  grid: [],
  items: {}
})

// UI State for the "Seed Menu"
const showSeedMenu = ref(false)
const selectedPlot = ref({ r: -1, c: -1 })

// Mapping Item Numbers to Emojis (Visuals)
// In the future, this will come from your ProductService/Database
const itemIcons = {
  "1": "🥕", // Carrot
  "2": "🌽", // Corn
  "3": "🍅"  // Tomato
}

        // 1. FETCH GARDEN & PRODUCTS
        const inventory = ref({})
        const products = ref([])
        const productMap = ref({}) // CatID -> ProductName

        const fetchData = async () => {
            try {
                const [gardenRes, profileRes, marketRes] = await Promise.all([
                    api.get('/garden'),
                    api.get('/profile'),
                    api.get('/market')
                ])
                garden.value = gardenRes.data.layoutData
                inventory.value = profileRes.data.inventory || {}
                
                // Build a map of CategoryID -> Product Name
                // We use the first product found for a category as the "Seed Name"
                marketRes.data.forEach(p => {
                    if (p.category) {
                         const catId = String(p.category.id || p.category);
                         if (!productMap.value[catId]) {
                             productMap.value[catId] = p.name;
                         }
                    }
                })
                
                loading.value = false
            } catch (error) {
                console.error("Failed to load data:", error)
                loading.value = false
            }
        }
        
        // 2. HANDLE CLICK
        const handlePlotClick = (rowIndex, colIndex) => {
          const cellValue = garden.value.grid[rowIndex][colIndex]

          if (cellValue === 0) {
            // EMPTY PLOT -> Open Seed Menu
            selectedPlot.value = { r: rowIndex, c: colIndex }
            showSeedMenu.value = true
          } else {
            // PLANTED PLOT -> Harvest
            performAction(rowIndex, colIndex, "HARVEST", null)
          }
        }

        // 3. SEND ACTION TO BACKEND
        const performAction = async (r, c, actionType, itemName) => {
          try {
            showSeedMenu.value = false
            const actionItemMap = {}
            actionItemMap[actionType] = itemName || "None"

            const payload = {
              rowIndex: r,
              colIndex: c,
              theme: "DEFAULT_FARM",
              actionItem: actionItemMap
            }

            const response = await api.post('/garden/action', payload)
            garden.value = response.data.layoutData
            
            // Update local inventory if planting
            if(actionType === 'PLANT' && itemName) {
                updateInventory()
            } else if (actionType === 'HARVEST') {
                 // alert("Harvested! Gained +5 Health, +2 Fame.")
                 updateInventory()
            }

          } catch (error) {
            console.error("Action failed:", error)
            alert("Could not perform action! " + (error.response?.data?.message || ""))
          }
        }

        onMounted(() => {
            fetchData()
        })

        // ... existing code ...

        // Update inventory after plant refetch
        const updateInventory = async () => {
             const profileRes = await api.get('/profile')
             inventory.value = profileRes.data.inventory || {}
        }
</script>

<template>
  <div class="doodle-container">
    <RouterLink to="/dashboard" class="back-link">← Back</RouterLink>

    <div class="paper-card garden-panel">
      <div class="tape"></div>
      <h1>My Garden</h1>

      <div v-if="loading">Loading Soil...</div>

      <div v-else class="garden-grid">
        <div 
          v-for="(row, rIndex) in garden.grid" 
          :key="rIndex" 
          class="grid-row"
        >
          <div 
            v-for="(cellValue, cIndex) in row" 
            :key="cIndex" 
            class="plot" 
            :class="{ 'empty': cellValue === 0 }"
            @click="handlePlotClick(rIndex, cIndex)"
          >
            <span v-if="cellValue !== 0" class="crop-icon">
              {{ itemIcons[cellValue] || '🌱' }}
            </span>
            <span v-else class="dirt-hint">Dirt</span>
          </div>
        </div>
      </div>
    </div>

    <div v-if="showSeedMenu" class="modal-overlay">
      <div class="paper-card modal-card">
        <h3>What to plant?</h3>
        <div class="seed-options">
          <div v-if="Object.keys(inventory).length === 0">No seeds available! Go to Market.</div>
          <button 
             v-for="(count, catId) in inventory" 
             :key="catId"
             @click="performAction(selectedPlot.r, selectedPlot.c, 'PLANT', productMap[catId] || 'Unknown')"
             class="btn-doodle"
             :disabled="count <= 0"
          >
             {{ productMap[catId] || 'Seed #' + catId }} (x{{ count }})
          </button>
        </div>
        <button @click="showSeedMenu = false" class="close-btn">Cancel</button>
      </div>
    </div>

  </div>
</template>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Patrick+Hand&display=swap');

.doodle-container {
  font-family: 'Patrick Hand', cursive;
  background-color: #f9fdf9;
  background-image: radial-gradient(#2e8b57 0.5px, transparent 0.5px);
  background-size: 20px 20px;
  min-height: 100vh;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
}

.paper-card {
  background: white;
  padding: 2rem;
  border: 2px solid #2e8b57;
  border-radius: 2px 255px 3px 25px / 255px 5px 225px 5px;
  box-shadow: 10px 10px 0px rgba(46, 139, 87, 0.1);
  position: relative;
}

.garden-panel {
  width: 90%;
  max-width: 500px;
  text-align: center;
}

/* GRID STYLES */
.garden-grid {
  display: flex;
  flex-direction: column;
  gap: 10px;
  margin-top: 20px;
}

.grid-row {
  display: flex;
  justify-content: center;
  gap: 10px;
}

.plot {
  width: 80px;
  height: 80px;
  border: 2px dashed #8d6e63; /* Brown dirt border */
  background-color: #efebe9; /* Light dirt color */
  border-radius: 10px;
  display: flex;
  align-items: center;
  justify-content: center;
  cursor: pointer;
  font-size: 2.5rem;
  transition: transform 0.1s;
}

.plot:hover {
  transform: scale(1.05);
  background-color: #d7ccc8;
}

.plot.empty {
  opacity: 0.7;
}

.dirt-hint {
  font-size: 1rem;
  color: #8d6e63;
}

/* MODAL STYLES */
.modal-overlay {
  position: fixed;
  top: 0; left: 0; width: 100%; height: 100%;
  background: rgba(0,0,0,0.3);
  display: flex;
  justify-content: center;
  align-items: center;
  z-index: 100;
}

.modal-card {
  width: 300px;
  text-align: center;
}

.seed-options {
  display: flex;
  flex-direction: column;
  gap: 10px;
  margin: 20px 0;
}

.btn-doodle {
  background: #2e8b57;
  color: white;
  border: 2px solid #2e8b57;
  border-radius: 255px 15px 225px 15px / 15px 225px 15px 255px;
  font-family: 'Patrick Hand', cursive;
  font-size: 1.2rem;
  cursor: pointer;
}

.close-btn {
  background: none;
  border: none;
  font-family: 'Patrick Hand', cursive;
  font-size: 1rem;
  text-decoration: underline;
  cursor: pointer;
  color: #666;
}

.tape {
  background-color: rgba(200, 230, 201, 0.6);
  width: 120px;
  height: 35px;
  position: absolute;
  top: -15px; left: 50%;
  transform: translateX(-50%) rotate(-1deg);
}

.back-link {
  position: absolute;
  top: 20px; left: 20px;
  text-decoration: none;
  color: #2e8b57;
  font-size: 1.2rem;
}
</style>