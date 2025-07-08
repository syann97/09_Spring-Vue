<script setup>
import api from '@/api/boardApi';
import { ref, computed, reactive, watch } from 'vue';
import moment from 'moment';
import { useRoute, useRouter } from 'vue-router';

const cr = useRoute(); // 현재 라우트 정보
const router = useRouter(); // 라우터 인스턴스

const page = ref({}); // 페이지 데이터 저장
const articles = computed(() => page.value.list || []); // <- 수정

// 페이지 요청 객체 (서버 PageRequest와 동일 구조) <- 추가
const pageRequest = reactive({
  page: parseInt(cr.query.page) || 1, // URL 쿼리에서 페이지 추출
  amount: parseInt(cr.query.amount) || 10, // URL 쿼리에서 데이터 수 추출
});

// 페이지네이션 페이지 변경 처리 <- 추가
const handlePageChange = async (pageNum) => {
  // URL 변경 → query 파트만 변경되므로 컴포넌트가 다시 마운트되지 않음
  // watch를 통해 cr이 변경됨을 감지하여 페이지 로드해야 함
  router.push({
    query: {
      page: pageNum,
      amount: pageRequest.amount,
    },
  });
};

// pageRequest의 값 변경된 경우 자동 호출 <- 추가
watch(cr, async (newValue) => {
  console.log('WATCH', cr.query.page);

  // URL 쿼리 파라미터를 pageRequest에 동기화
  pageRequest.page = parseInt(cr.query.page) || 1;
  pageRequest.amount = parseInt(cr.query.amount) || 10;

  // 새로운 페이지 데이터 로드
  await load(pageRequest);
});

// 게시글 목록 로드
const load = async (query) => {
  try {
    page.value = await api.getList(query);
    console.log(page.value);
  } catch (error) {
    console.error('목록 로드 실패:', error);
  }
};

load(); // 컴포넌트 마운트 시 실행
</script>

<template>
  <div>
    <h1 class="mb-3"><i class="fa-solid fa-paste"></i> 게시글 목록</h1>

    <!-- 전체 건수 표시 -->
    <div class="mt-5 text-end">(총 {{ page.totalCount }}건)</div>

    <!-- 게시글 테이블 -->
    <table class="table table-striped">
      <thead>
        <tr>
          <th style="width: 60px">No</th>
          <th>제목</th>
          <th style="width: 200px">작성자</th>
          <th style="width: 120px">작성일</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="article in articles" :key="article.no">
          <td>{{ article.no }}</td>
          <td>
            <!-- 상세보기로 이동하는 링크 -->
            <router-link
              :to="{
                name: 'board/detail',
                params: { no: article.no },
                query: cr.query,
              }"
            >
              {{ article.title }}
            </router-link>
          </td>
          <td>
            <img
              :src="`/api/member/${article.writer}/avatar`"
              style="height: 20px"
            />
            {{ article.writer }}
          </td>
          <!-- 해당 양식으로 표시 -->
          <td>{{ moment(article.regDate).format('YYYY-MM-DD') }}</td>
        </tr>
      </tbody>
    </table>

    <!-- 하단 버튼 영역 -->
    <div class="my-5 d-flex">
      <div class="flex-grow-1 text-center">
        <!-- 페이지네이션 컴포넌트 -->
        <vue-awesome-paginate
          :total-items="page.totalCount"
          :items-per-page="pageRequest.amount"
          :max-pages-shown="5"
          :show-ending-buttons="true"
          v-model="pageRequest.page"
          @click="handlePageChange"
        >
          <!-- 커스텀 아이콘 슬롯 -->
          <template #first-page-button>
            <i class="fa-solid fa-backward-fast"></i>
          </template>
          <template #prev-button>
            <i class="fa-solid fa-caret-left"></i>
          </template>
          <template #next-button>
            <i class="fa-solid fa-caret-right"></i>
          </template>
          <template #last-page-button>
            <i class="fa-solid fa-forward-fast"></i>
          </template>
        </vue-awesome-paginate>
      </div>
      <div>
        <router-link
          :to="{ name: 'board/create', query: cr.query }"
          class="btn btn-primary"
        >
          <i class="fa-solid fa-pen-to-square"></i> 글 작성
        </router-link>
      </div>
    </div>
  </div>
</template>
