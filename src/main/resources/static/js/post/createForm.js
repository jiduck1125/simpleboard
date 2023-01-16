document.getElementById('title').addEventListener('input', (e) => {
    if (e.target.value) {
        e.target.classList.remove('is-invalid');
        document.getElementById('invalidFeedbackTitle').style.display = 'none';
    }
});

document.getElementById('content').addEventListener('input', (e) => {
    if (e.target.value) {
        e.target.classList.remove('is-invalid');
        document.getElementById('invalidFeedbackContent').style.display = 'none';
    }
});