document.getElementById('loginForm').addEventListener('submit', function(e) {
    e.preventDefault();
    const username = document.getElementById('username').value;
    const password = document.getElementById('password').value;

    // 调用登录API
    fetch('/myapp/login', {  // Updated endpoint
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        },
        body: `username=${username}&password=${password}`
    })
    .then(response => response.json())  // Assuming your backend returns a JSON response
    .then(data => {
        if (data.success) {
            // 登录成功，跳转到 billManagement 页面
            window.location.href = '/myapp/billManagement.html';
        } else {
            // 处理登录失败的情况
            document.getElementById('errorMessage').style.display = 'block';
        }
    })
    .catch(error => {
        console.error('Error during login:', error);
    });
});
