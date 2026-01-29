<script setup>
import { ref, onMounted } from 'vue'
import { useRouter } from 'vue-router'

const userName = ref('Farmer')
const router = useRouter()

onMounted(() => {
  // 1. Get User Info from Storage
  userName.value = localStorage.getItem('username')
})

const handleLogout = () => {
  // 1. Clear Data
  localStorage.removeItem('token')
  localStorage.removeItem('fullName')
  
  // 2. Redirect to Guest Home
  router.push('/')
}

</script>

<template>
  <div class="doodle-container">
    <nav class="doodle-nav">
      <span class="logo">Makizone 🌿</span>
      <button @click="handleLogout" class="btn-small red">Log Out</button>
    </nav>

    <div class="dashboard-content">
      <div class="paper-card header-card">
        <div class="tape"></div>
        <h1>Welcome back, {{ userName }}!</h1>
        <p>Your garden is waiting. What would you like to do?</p>
      </div>

      <div class="grid-menu">
        
        <div class="paper-card menu-item">
          <h2>🌱 My Garden</h2>
          <p>Water plants & harvest crops.</p>
          <button @click="router.push('/garden')" class="btn-doodle">Enter Garden</button>
        </div>

        <div class="paper-card menu-item">
          <h2>💰 The Market</h2>
          <p>Sell crops & buy seeds.</p>
          <button class="btn-doodle">Go to Market</button>
        </div>

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
  color: #2e8b57;
}

.doodle-nav {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 1rem 2rem;
  background: white;
  border-bottom: 2px dashed #2e8b57;
  box-shadow: 0 4px 0 rgba(46, 139, 87, 0.1);
}

.logo {
  font-size: 2rem;
  font-weight: bold;
}

.dashboard-content {
  padding: 2rem;
  max-width: 900px;
  margin: 0 auto;
}

.paper-card {
  background: white;
  padding: 2rem;
  position: relative;
  border: 2px solid #2e8b57;
  border-radius: 2px 255px 3px 25px / 255px 5px 225px 5px;
  box-shadow: 8px 8px 0px rgba(46, 139, 87, 0.1);
  margin-bottom: 2rem;
}

.header-card {
  text-align: center;
}

.tape {
  background-color: rgba(200, 230, 201, 0.6);
  width: 120px;
  height: 35px;
  position: absolute;
  top: -15px;
  left: 50%;
  transform: translateX(-50%) rotate(1deg);
}

.grid-menu {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(300px, 1fr));
  gap: 2rem;
}

.menu-item {
  text-align: center;
  transition: transform 0.2s;
}

.menu-item:hover {
  transform: translateY(-5px);
}

h1 { font-size: 2.5rem; margin-bottom: 0.5rem; }
h2 { font-size: 1.8rem; margin-bottom: 0.5rem; }

.btn-doodle {
  width: 100%;
  font-family: 'Patrick Hand', cursive;
  font-size: 1.2rem;
  padding: 8px;
  cursor: pointer;
  background-color: #2e8b57;
  color: white;
  border: 2px solid #2e8b57;
  border-radius: 255px 15px 225px 15px / 15px 225px 15px 255px;
  margin-top: 1rem;
}

.btn-small {
  font-family: 'Patrick Hand', cursive;
  font-size: 1rem;
  padding: 5px 15px;
  cursor: pointer;
  background: white;
  color: #2e8b57;
  border: 2px solid #2e8b57;
  border-radius: 15px 225px 15px 255px / 255px 15px 225px 15px;
}

.btn-small.red:hover {
  background: #fee2e2;
  color: #dc2626;
  border-color: #dc2626;
}
</style>