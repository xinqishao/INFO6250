document.getElementById('loginForm').addEventListener('submit', function(e) {
    e.preventDefault();
    const username = document.getElementById('username').value;
    const password = document.getElementById('password').value;

    // 调用登录API
    fetch('/myapp/perform_login', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        },
        body: `username=${username}&password=${password}`
    })
    .then(response => {
        if (response.ok) {
            // 登录成功，跳转到 billManagement 页面
            window.location.href = '/myapp/billManagement.html';
        } else {
            // 处理登录失败的情况
            alert('Login failed, please check your username and password.');
        }
    })
    .catch(error => {
        console.error('Error during login:', error);
    });
});
