<script setup>
import { ref } from 'vue'
import { useRouter } from 'vue-router'
import api from '../services/api'

const fullName = ref('')
const email = ref('')
const password = ref('')
const address = ref('')
const errorMessage = ref('')
const router = useRouter()

const handleRegister = async () => {
  errorMessage.value = ''
  try{
	const response = await api.post('/auth/register', {
	fullName: fullName.value,
	email: email.value,
	password: password.value,
	address: {
		 fullAddress: address.value
		}
	})
	console.log("Registering:", fullName.value, email.value)
	alert("Creating your garden...")
	router.push('/login')
  }
  catch(error){
	console.error("Registration process has failed:", error)
	if(error.response && error.response.status == 401){
	errorMessage.value = "Wrong email or password!"

	}
	else{
	errorMessage.value = "Server is sleeping. Try again later."
	}
  }
  
}
</script>

<template>
  <div class="doodle-container">
    <RouterLink to="/" class="back-link">← Back to Garden</RouterLink>
    
    <div class="paper-card">
      <div class="tape"></div>
      <h1>New Farmer?</h1>
      
      <form @submit.prevent="handleRegister">
        <div class="input-group">
          <label>Full Name</label>
          <input type="text" v-model="fullName" required placeholder="Oran The Planter" />
        </div>
        
        <div class="input-group">
          <label>Delivery Address</label>
          <input type="text" v-model="address" required placeholder="123 Green St." />
        </div>
        
        <div class="input-group">
          <label>Email</label>
          <input type="email" v-model="email" required />
        </div>
        
        <div class="input-group">
          <label>Password</label>
          <input type="password" v-model="password" required />
        </div>
        
        <button type="submit" class="btn-doodle green">Start Farming</button>
      </form>
      
      <p class="switch-link">
        Already have a plot? <RouterLink to="/login">Log In</RouterLink>
      </p>
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
  color: #2e8b57;
}

.paper-card {
  background: white;
  padding: 3rem;
  width: 100%;
  max-width: 400px;
  position: relative;
  border: 2px solid #2e8b57;
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
}

.switch-link { 
  text-align: center; 
  margin-top: 1.5rem; 
}

.switch-link a { 
  color: #2e8b57; 
  font-weight: bold; 
}
</style>