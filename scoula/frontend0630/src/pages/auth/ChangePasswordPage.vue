<script setup>
import { computed, reactive, ref } from 'vue';
import { useAuthStore } from '@/stores/auth';
import { useRouter } from 'vue-router';
import authApi from '@/api/authApi';
const router = useRouter();
const auth = useAuthStore();
const changePassword = reactive({
  username: auth.username,
  oldPassword: '',
  newPassword: '',
  newPassword2: '',
});
const disableSubmit = computed(
  () =>
    !changePassword.oldPassword ||
    !changePassword.newPassword ||
    !changePassword.newPassword2
);
const error = ref('');
const inputPassword = () => (error.value = '');
const resetError = () => (error.value = '');
const onSubmit = async () => {
  if (changePassword.newPassword != changePassword.newPassword2) {
    error.value = '새 비밀번호가 일치하지 않습니다.';
    return;
  }
  try {
    await authApi.changePassword(changePassword);
    alert('비밀번호를 수정했습니다.');
    router.push({ name: 'profile' });
  } catch (e) {
    error.value = e.response.data;
  }
};
</script>
