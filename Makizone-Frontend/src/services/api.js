// src/services/api.js
import axios from 'axios'

// Create a "Custom Phone" tuned to your specific backend
const api = axios.create({
  baseURL: 'http://localhost:8080/api', // The address of the Pizza Shop
  headers: {
    'Content-Type': 'application/json' // We speak JSON
  }
})

export default api
