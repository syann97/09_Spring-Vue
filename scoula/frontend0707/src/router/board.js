// 게시판 라우팅
import { isAuthenticated } from '@/util/guard';

export default [
  // 게시글 목록 조회
  {
    path: '/board/list',
    name: 'board/list',
    component: () => import('../pages/board/BoardListPage.vue'),
  },

  // 게시글 상세 조회
  {
    path: '/board/detail/:no', // URL 파라미터로 게시글 번호 전달
    name: 'board/detail',
    component: () => import('../pages/board/BoardDetailPage.vue'),
  },

  {
    path: '/board/create',
    name: 'board/create',
    component: () => import('../pages/board/BoardCreatePage.vue'),
    beforeEnter: isAuthenticated, // 글쓰기 페이지 보호
  },
  {
    path: '/board/update/:no',
    name: 'board/update',
    component: () => import('../pages/board/BoardUpdatePage.vue'),
    beforeEnter: isAuthenticated, // 글수정 페이지 보호
  },
];
