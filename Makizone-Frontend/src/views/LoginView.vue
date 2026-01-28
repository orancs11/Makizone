<script setup>
import { ref } from 'vue'
import { useRouter } from 'vue-router'

// 1. THE BRAIN (State)
// 'ref' makes a variable "Reactive". If you change it here, it updates on screen instantly.
const email = ref('')
const password = ref('')
const router = useRouter()

// 2. THE ACTION
const handleLogin = () => {
  // We will add the Axios call here later!
  console.log("Logging in with:", email.value, password.value)
  alert("Trying to login... (Check Console)")
}
</script>

<template>
  <div class="doodle-container">
    <RouterLink to="/" class="back-link">← Back to Garden</RouterLink>
    
    <div class="paper-card login-card">
      <div class="tape"></div>
      
      <h1>Unlock Garden</h1>
      
      <form @submit.prevent="handleLogin">
        <div class="input-group">
          <label>Email</label>
          <input type="email" v-model="email" required placeholder="farmer@makizone.com" />
        </div>
        
        <div class="input-group">
          <label>Password</label>
          <input type="password" v-model="password" required placeholder="••••••" />
        </div>
        
        <button type="submit" class="btn-doodle green">Enter</button>
      </form>
      
      <p class="switch-link">
        New here? <RouterLink to="/register">Get a Plot</RouterLink>
      </p>
    </div>
  </div>
</template>

<style scoped>
/* Reuse the Doodle Font */
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
  color: #2e8b57;
}

.paper-card {
  background: white;
  padding: 3rem;
  width: 100%;
  max-width: 400px;
  position: relative;
  border: 2px solid #2e8b57;
  /* Sketchy Border Radius */
  border-radius: 2px 255px 3px 25px / 255px 5px 225px 5px;
  box-shadow: 10px 10px 0px rgba(46, 139, 87, 0.1);
}

.tape {
  background-color: rgba(200, 230, 201, 0.6);
  width: 120px;
  height: 35px;
  position: absolute;
  top: -15px;
  left: 50%;
  transform: translateX(-50%) rotate(-2deg);
}

h1 {
  text-align: center;
  font-size: 2.5rem;
  margin-bottom: 2rem;
}

.input-group {
  margin-bottom: 1.5rem;
  text-align: left;
}

label {
  display: block;
  font-size: 1.2rem;
  margin-bottom: 0.5rem;
  font-weight: bold;
}

input {
  width: 100%;
  padding: 10px;
  font-family: 'Patrick Hand', cursive;
  font-size: 1.2rem;
  border: 2px dashed #2e8b57;
  border-radius: 5px;
  outline: none;
  background: #f0fdf4;
}

input:focus {
  border-style: solid;
  background: white;
}

.btn-doodle {
  width: 100%;
  font-family: 'Patrick Hand', cursive;
  font-size: 1.5rem;
  padding: 10px;
  cursor: pointer;
  background-color: #2e8b57;
  color: white;
  border: 2px solid #2e8b57;
  border-radius: 255px 15px 225px 15px / 15px 225px 15px 255px;
  margin-top: 1rem;
  transition: transform 0.2s;
}

.btn-doodle:hover {
  transform: scale(1.02);
}

.back-link {
  position: absolute;
  top: 20px;
  left: 20px;
  text-decoration: none;
  color: #2e8b57;
  font-size: 1.2rem;
}

.switch-link {
  text-align: center;
  margin-top: 1.5rem;
  font-size: 1.1rem;
}

.switch-link a {
  color: #2e8b57;
  font-weight: bold;
}
</style>