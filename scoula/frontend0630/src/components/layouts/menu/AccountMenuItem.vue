<!-- 계정 메뉴 아이템 컴포넌트 -->
<script setup>
const props = defineProps({ username: String });

const avatar = `/api/member/${props.username}/avatar`;
</script>

<template>
  <li class="nav-item">
    <router-link class="nav-link" to="/auth/profile">
      <img :src="avatar" class="avatar avatar-sm" />
      {{ username }}
    </router-link>
  </li>
</template>
<style></style>
