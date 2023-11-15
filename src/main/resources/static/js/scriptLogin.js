document.getElementById('eye-toggle').addEventListener('click', function () {
    var passwordInput = document.getElementById('password');
    var eyeIcon = document.querySelector('#eye-toggle i');
    if (passwordInput.type === 'password') {
      passwordInput.type = 'text';
      eyeIcon.classList.remove('fa-eye-slash');
      eyeIcon.classList.add('fa-eye');
    } else {
      passwordInput.type = 'password';
      eyeIcon.classList.remove('fa-eye');
      eyeIcon.classList.add('fa-eye-slash');
    }
  });