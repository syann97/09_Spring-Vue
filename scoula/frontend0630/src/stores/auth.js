import { ref, computed } from 'vue';
import { defineStore } from 'pinia';
import axios from 'axios';

const initState = {
  token: '',
  user: {
    username: '', // 사용자 ID
    email: '', // Email
    roles: [],
  },
};

// 권한 목록 저장소
export const useAuthStore = defineStore('auth', () => {
  const state = ref({ ...initState });

  const isLogin = computed(() => !!state.value.user.username); // 로그인 여부
  const username = computed(() => state.value.user.username); // 사용자 ID
  const email = computed(() => state.value.user.email); // 사용자 Email

  const login = async (member) => {
    // 실제로는 API에서 token 등을 받아야 함
    state.value.token = 'test token';
    state.value.user = {
      username: member.username,
      email: member.username + '@test.com',
      roles: ['ROLE_MEMBER'],
    };

    // api 호출
    const { data } = await axios.post('/api/auth/login', member);
    state.value = { ...data };
    localStorage.setItem('auth', JSON.stringify(state.value));
  };

  const logout = () => {
    localStorage.clear();
    state.value = { ...initState };
  };

  const getToken = () => state.value.token;

  const load = () => {
    const auth = localStorage.getItem('auth');
    if (auth != null) {
      state.value = JSON.parse(auth);
      console.log('auth loaded:', state.value);
    }
  };

  load();

  return {
    state,
    username,
    email,
    isLogin,
    login,
    logout,
    getToken,
  };
});
