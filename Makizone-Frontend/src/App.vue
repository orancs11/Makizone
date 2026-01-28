<script setup>
import { RouterView } from 'vue-router'
</script>

<template>
  <RouterView />
</template>

<style>
/* GLOBAL RESET */
/* We put this here to ensure no white edges around the page */
body {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
  background-color: #f9fdf9; /* Match our paper color */
}
</style>