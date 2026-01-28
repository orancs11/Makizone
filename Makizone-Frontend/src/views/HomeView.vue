<template>
  <div class="doodle-container">
    
    <nav class="navbar">
      <div class="logo">🍏 Makizone</div>
      <div class="nav-links">
        <RouterLink to="/login" class="nav-item">Log In</RouterLink>
        <RouterLink to="/signup" class="nav-item signup">Sign Up</RouterLink>
      </div>
    </nav>

    <main class="hero">
      <h1 class="wiggle">Grow Real Food.</h1>
      <p class="subtitle">A game where your garden feeds you IRL.</p>

      <div class="card-row">
        
        <div class="paper-card market">
          <div class="tape"></div>
          <div class="icon">🥦</div>
          <h2>The Market</h2>
          <p>Buy fresh veggies directly from local farmers.</p>
          <button class="btn-doodle green">Shop Now</button>
        </div>

        <div class="paper-card garden">
          <div class="tape"></div>
          <div class="icon">🌻</div>
          <h2>My Garden</h2>
          <p>Design your grid. Earn badges. Show off.</p>
          <button class="btn-doodle outline">Play Game</button>
        </div>

      </div>
    </main>

    <div class="leaf-decor top-left">🌿</div>
    <div class="leaf-decor bottom-right">🌱</div>

  </div>
</template>

<script setup>
import { RouterLink } from 'vue-router'
</script>

<style scoped>
/* IMPORT HANDWRITTEN FONT */
@import url('https://fonts.googleapis.com/css2?family=Patrick+Hand&display=swap');

/* --- GLOBAL DOODLE THEME --- */
.doodle-container {
  font-family: 'Patrick Hand', cursive; /* The doodle font */
  background-color: #f9fdf9; /* Very light green paper color */
  background-image: radial-gradient(#2e8b57 0.5px, transparent 0.5px); /* Dot grid pattern */
  background-size: 20px 20px;
  min-height: 100vh;
  color: #2e8b57; /* Forest Green text */
  display: flex;
  flex-direction: column;
  overflow: hidden;
  position: relative;
}

/* --- NAVBAR --- */
.navbar {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 1.5rem 3rem;
  border-bottom: 2px dashed #2e8b57; /* Dashed line like a notebook */
  background: white;
}

.logo {
  font-size: 2rem;
  font-weight: bold;
  transform: rotate(-2deg); /* Slight tilt */
}

.nav-item {
  text-decoration: none;
  color: #2e8b57;
  font-size: 1.3rem;
  margin-left: 2rem;
}

.signup {
  border: 2px solid #2e8b57;
  padding: 5px 15px;
  /* Irregular border radius for hand-drawn look */
  border-radius: 255px 15px 225px 15px / 15px 225px 15px 255px;
  transition: all 0.2s;
}

.signup:hover {
  background-color: #2e8b57;
  color: white;
  transform: scale(1.1) rotate(2deg);
}

/* --- HERO --- */
.hero {
  flex: 1;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  padding: 2rem;
}

h1 {
  font-size: 5rem;
  margin: 0;
  text-shadow: 3px 3px 0px #a7f3d0; /* Offset shadow */
}

.wiggle {
  animation: wiggle 3s ease-in-out infinite;
}

.subtitle {
  font-size: 1.8rem;
  margin-top: 0;
  margin-bottom: 3rem;
  color: #4a7c59;
}

/* --- CARDS --- */
.card-row {
  display: flex;
  gap: 3rem;
  flex-wrap: wrap;
  justify-content: center;
}

.paper-card {
  background: white;
  width: 280px;
  padding: 2rem;
  position: relative;
  text-align: center;
  box-shadow: 5px 5px 0px rgba(46, 139, 87, 0.2);
  border: 2px solid #2e8b57;
  /* The "Sketchy Box" trick */
  border-radius: 2px 255px 5px 25px / 255px 5px 225px 5px;
  transition: transform 0.3s;
}

.paper-card:hover {
  transform: translateY(-10px) rotate(1deg);
}

/* The "Scotch Tape" look */
.tape {
  background-color: rgba(200, 230, 201, 0.6);
  width: 100px;
  height: 30px;
  position: absolute;
  top: -15px;
  left: 35%;
  transform: rotate(-3deg);
  box-shadow: 0 1px 3px rgba(0,0,0,0.1);
}

.icon {
  font-size: 3.5rem;
  margin-bottom: 1rem;
}

h2 {
  font-size: 2rem;
  margin-bottom: 0.5rem;
}

p {
  font-size: 1.2rem;
  line-height: 1.4;
}

/* --- BUTTONS --- */
.btn-doodle {
  font-family: 'Patrick Hand', cursive;
  font-size: 1.3rem;
  padding: 10px 25px;
  cursor: pointer;
  border: 2px solid #2e8b57;
  border-radius: 255px 15px 225px 15px / 15px 225px 15px 255px;
  margin-top: 1rem;
  transition: 0.2s;
}

.btn-doodle.green {
  background-color: #2e8b57;
  color: white;
}

.btn-doodle.outline {
  background-color: transparent;
  color: #2e8b57;
}

.btn-doodle:hover {
  transform: scale(1.05);
  box-shadow: 3px 3px 0px #a7f3d0;
}

/* --- BACKGROUND DECOR --- */
.leaf-decor {
  position: absolute;
  font-size: 5rem;
  opacity: 0.2;
  pointer-events: none;
}

.top-left { top: 100px; left: 50px; transform: rotate(-45deg); }
.bottom-right { bottom: 50px; right: 50px; transform: rotate(20deg); }

/* ANIMATION */
@keyframes wiggle {
  0%, 100% { transform: rotate(-2deg); }
  50% { transform: rotate(2deg); }
}
</style>