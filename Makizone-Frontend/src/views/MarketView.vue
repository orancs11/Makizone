<script setup>
import { ref, onMounted } from 'vue'
import api from '../services/api' 

const products = ref([])
const loading = ref(true)

const fetchProducts = async () => {
    try {
        const response = await api.get('/market')
        products.value = response.data
    } catch (error) {
        console.error("Failed to load products:", error)
    } finally {
        loading.value = false
    }
}

const buyProduct = async (product) => {
    try {
        await api.post('/market/buy', {
            productId: product.id,
            quantity: 1
        })
        alert(`You bought a ${product.name}!`)
    } catch (error) {
        console.error("Purchase failed:", error)
        alert("Could not purchase item.")
    }
}

onMounted(() => {
    fetchProducts()
})
</script>

<template>
  <div class="doodle-container">
    <div class="paper-card market-panel">
      <div class="tape"></div>
      <h1>Seed Market</h1>
      <p style="margin-bottom:20px; color:#555;">Buy seeds to plant in your garden!</p>

      <div v-if="loading">Loading Market...</div>

      <div v-else class="market-grid">
        <div v-for="product in products" :key="product.id" class="product-card">
           <div class="product-icon">🌱</div> 
           <!-- Using generic icon, ideally use product.category or dedicated icon -->
           <h3>{{ product.name }}</h3>
           <p>{{ product.description }}</p>
           <button @click="buyProduct(product)" class="btn-doodle">Buy (Free)</button>
        </div>
      </div>
       <RouterLink to="/dashboard" class="back-link">← Back</RouterLink>
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
  width: 90%;
  max-width: 800px;
  text-align: center;
}

.tape {
  background-color: rgba(200, 230, 201, 0.6);
  width: 120px;
  height: 35px;
  position: absolute;
  top: -15px; left: 50%;
  transform: translateX(-50%) rotate(-1deg);
}

.market-grid {
    display: grid;
    grid-template-columns: repeat(auto-fill, minmax(200px, 1fr));
    gap: 20px;
    margin-top: 20px;
}

.product-card {
    border: 2px dashed #2e8b57;
    padding: 15px;
    border-radius: 10px;
    display: flex;
    flex-direction: column;
    align-items: center;
    gap: 10px;
}

.product-icon {
    font-size: 3rem;
}

.btn-doodle {
  background: #2e8b57;
  color: white;
  border: 2px solid #2e8b57;
  border-radius: 255px 15px 225px 15px / 15px 225px 15px 255px;
  font-family: 'Patrick Hand', cursive;
  font-size: 1.2rem;
  cursor: pointer;
  padding: 5px 15px;
}

.back-link {
  display: inline-block;
  margin-top: 20px;
  text-decoration: none;
  color: #2e8b57;
  font-size: 1.2rem;
}
</style>
