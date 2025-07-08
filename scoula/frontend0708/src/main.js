import './assets/main.css';
import 'bootstrap/dist/css/bootstrap.css'; // Bootstrap CSS 추가
import '@fortawesome/fontawesome-free/css/all.min.css';
import 'vue-awesome-paginate/dist/style.css'; // 페이지네이션 스타일

import { createApp } from 'vue';
import { createPinia } from 'pinia';
import VueAwesomePaginate from 'vue-awesome-paginate'; // 페이지네이션 컴포넌트
import { useKakao } from 'vue3-kakao-maps/@utils';

import App from './App.vue';
import router from './router';

// JavaScript 키로 Kakao API 초기화
const rest_api_key = '발급받은 JavavScript 앱 키';
useKakao(rest_api_key, ['services']); // services 라이브러리 포함

const app = createApp(App);

app.use(VueAwesomePaginate); // 전역 컴포넌트 등록
app.use(createPinia()); // 상태 관리
app.use(router); // 라우팅

app.mount('#app');
