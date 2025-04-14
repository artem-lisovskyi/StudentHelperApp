document.addEventListener('DOMContentLoaded', function() {
    // Get elements
    const copyButton = document.querySelector('.result-menu img[alt="Копіювати"]');
    const reloadButton = document.querySelector('.result-menu img[alt="Повторити"]');
    const generatedText = document.getElementById('generated-text');
    const fileIdField = document.getElementById('fileId');

    // Copy functionality
    if (copyButton && generatedText) {
        copyButton.addEventListener('click', function() {
            const textToCopy = generatedText.textContent;
            navigator.clipboard.writeText(textToCopy)
                .then(() => {
                    // Show feedback
                    const originalSrc = copyButton.src;
                    copyButton.src = '/img/checkmark.png'; // Make sure this image exists
                    
                    setTimeout(() => {
                        copyButton.src = originalSrc;
                    }, 1500);
                })
                .catch(err => {
                    console.error('Failed to copy text: ', err);
                });
        });
    }

    // Reload functionality
    if (reloadButton) {
        reloadButton.addEventListener('click', function() {
            // Show loading state
            reloadButton.classList.add('spinning');
            generatedText.innerHTML = '<div class="loading-message">Регенерація конспекту...</div>';
            
            // Get the fileId from the hidden field
            const fileId = fileIdField ? fileIdField.value : null;
            
            // Redirect to regenerate endpoint with the fileId
            window.location.href = `/summarize/regenerate${fileId ? '?id=' + fileId : ''}`;
        });
    }
}); 