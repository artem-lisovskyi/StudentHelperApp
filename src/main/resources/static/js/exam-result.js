document.addEventListener('DOMContentLoaded', function() {
    const questions = document.querySelectorAll('.question-container');
    const prevButton = document.getElementById('prev');
    const nextButton = document.getElementById('next');
    const currentPageElem = document.getElementById('current-page');
    let currentQuestion = 0;
    const totalQuestions = questions.length;
    
    // Function to update displayed question
    function updateQuestion() {
        // Hide all questions
        questions.forEach(q => q.style.display = 'none');
        
        // Show current question
        questions[currentQuestion].style.display = 'block';
        
        // Update pagination counter
        if (currentPageElem) {
            currentPageElem.textContent = (currentQuestion + 1).toString();
        }
        
        // Update button visibility
        if (prevButton) {
            prevButton.style.opacity = currentQuestion === 0 ? '0.5' : '1';
            prevButton.style.pointerEvents = currentQuestion === 0 ? 'none' : 'auto';
        }
        
        if (nextButton) {
            nextButton.style.opacity = currentQuestion === totalQuestions - 1 ? '0.5' : '1';
            nextButton.style.pointerEvents = currentQuestion === totalQuestions - 1 ? 'none' : 'auto';
        }
    }
    
    // Show only first question initially
    updateQuestion();
    
    // Navigation buttons event listeners
    if (prevButton) {
        prevButton.addEventListener('click', function(e) {
            e.preventDefault();
            if (currentQuestion > 0) {
                currentQuestion--;
                updateQuestion();
            }
        });
    }
    
    if (nextButton) {
        nextButton.addEventListener('click', function(e) {
            e.preventDefault();
            if (currentQuestion < totalQuestions - 1) {
                currentQuestion++;
                updateQuestion();
            }
        });
    }
    
    // Add keyboard navigation
    document.addEventListener('keydown', function(e) {
        if (e.key === 'ArrowLeft' && currentQuestion > 0) {
            currentQuestion--;
            updateQuestion();
        } else if (e.key === 'ArrowRight' && currentQuestion < totalQuestions - 1) {
            currentQuestion++;
            updateQuestion();
        }
    });
    
    // Add swipe functionality for mobile
    let touchStartX = 0;
    let touchEndX = 0;
    
    document.addEventListener('touchstart', function(e) {
        touchStartX = e.changedTouches[0].screenX;
    }, false);
    
    document.addEventListener('touchend', function(e) {
        touchEndX = e.changedTouches[0].screenX;
        handleSwipe();
    }, false);
    
    function handleSwipe() {
        const threshold = 50;
        if (touchEndX < touchStartX - threshold) {
            // Swiped left (next)
            if (currentQuestion < totalQuestions - 1) {
                currentQuestion++;
                updateQuestion();
            }
        } else if (touchEndX > touchStartX + threshold) {
            // Swiped right (previous)
            if (currentQuestion > 0) {
                currentQuestion--;
                updateQuestion();
            }
        }
    }
}); 