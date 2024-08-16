document.addEventListener('DOMContentLoaded', function() {
    loadBills();

    // Get modal elements
    var modal = document.getElementById("addBillModal");
    var btn = document.getElementById("openAddBillModal");
    var span = document.getElementsByClassName("close")[0];

    // When the user clicks the button, open the modal 
    btn.onclick = function() {
        modal.style.display = "block";
    }

    // When the user clicks on <span> (x), close the modal
    span.onclick = function() {
        modal.style.display = "none";
    }

    // When the user clicks anywhere outside of the modal, close it
    window.onclick = function(event) {
        if (event.target == modal) {
            modal.style.display = "none";
        }
    }

    document.getElementById('billForm').addEventListener('submit', function(e) {
        e.preventDefault();
        const description = document.getElementById('description').value;
        const amount = document.getElementById('amount').value;

        // 调用添加账单的API
        fetch('/myapp/api/bills', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({ description, amount })
        })
        .then(response => {
            if (response.ok) {
                return response.json();
            }
            throw new Error('Failed to add bill');
        })
        .then(data => {
            console.log('Bill added:', data);
            loadBills();  // 重新加载账单列表
            modal.style.display = "none";  // Close the modal after adding the bill
        })
        .catch(error => {
            console.error('Error adding bill:', error);
        });
    });

    // 删除账单
    document.getElementById('deleteButton').addEventListener('click', function() {
        const selectedRow = document.querySelector('#billTable tbody tr.selected');
        if (selectedRow) {
            const billId = selectedRow.dataset.id;
            fetch(`/myapp/api/bills/${billId}`, {
                method: 'DELETE'
            })
            .then(response => {
                if (response.ok) {
                    loadBills();  // 重新加载账单列表
                } else {
                    throw new Error('Failed to delete bill');
                }
            })
            .catch(error => {
                console.error('Error deleting bill:', error);
            });
        } else {
            alert('Please select a bill to delete');
        }
    });
});

function loadBills() {
    console.log('loadBills function called');
    // 调用获取账单列表的API
    fetch('/myapp/api/bills')
        .then(response => {
            if (response.ok) {
                return response.json();
            }
            throw new Error('Failed to load bills');
        })
        .then(bills => {
            const billTableBody = document.querySelector('#billTable tbody');
            billTableBody.innerHTML = '';
            bills.forEach(bill => {
                const row = document.createElement('tr');
                row.dataset.id = bill.id;  // 保存账单ID，便于删除操作
                row.innerHTML = `
                    <td>${bill.description}</td>
                    <td>${bill.amount}</td>
                    <td><input type="radio" name="selectBill" /></td>
                `;
                row.addEventListener('click', function() {
                    document.querySelectorAll('#billTable tbody tr').forEach(tr => tr.classList.remove('selected'));
                    row.classList.add('selected');
                });
                billTableBody.appendChild(row);
            });
        })
        .catch(error => {
            console.error('Error loading bills:', error);
        });
}
