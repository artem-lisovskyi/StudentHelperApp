const uploadTrigger = document.getElementById('upload-trigger');
const fileInput = document.getElementById('file-input');
const fileName = document.getElementById('file-name');
const resultSection = document.getElementById('result-section');
const generatedText = document.getElementById('generated-text');
const mainAction = document.getElementById('main-action');
const conspectTitle = document.getElementById('conspect-title');
const submitButton = document.getElementById('submit-button');
const uploadForm = document.getElementById('upload-form');

// Убираем заголовок и описание при генерации
const heading = document.querySelector('.consp-content h1');
const description = document.querySelector('.consp-content p');

let fileUploaded = false;
let textGenerated = false;

uploadTrigger && uploadTrigger.addEventListener('click', function(e) {
    e.preventDefault();

    if (!fileUploaded) {
        fileInput.click();
    } else if (!textGenerated) {
        // Отправка формы на сервер
        submitButton.click();
    } else {
        // Поведение при загрузке
        alert("Завантаження нового PDF-файлу або скачування результату.");
    }
});

fileInput && fileInput.addEventListener('change', function() {
    const file = this.files[0];
    if (file) {
        fileName.style.display = 'block';
        fileName.textContent = "Файл: " + file.name;

        uploadTrigger.textContent = "Сгенерувати";
        mainAction.classList.add("generate-mode");
        fileUploaded = true;
        textGenerated = false;
    }
});

// Prevent double submission and show loading state
uploadForm && uploadForm.addEventListener('submit', function() {
    uploadTrigger.textContent = "Завантаження...";
    uploadTrigger.style.pointerEvents = 'none';
    
    // После получения ответа от сервера (будет выполняться на странице результата)
    // Эту часть можно адаптировать в зависимости от вашего серверного решения
    if (resultSection) {
        heading.style.display = 'none';
        description.style.display = 'none';

        resultSection.style.display = 'block';
        // Здесь будет текст полученный от сервера
        textGenerated = true;
    }
});

// Добавляем функциональность drag and drop
const dropZone = document.getElementById('main-action');

dropZone && dropZone.addEventListener('dragover', function(e) {
    e.preventDefault();
    this.classList.add('drag-over');
});

dropZone && dropZone.addEventListener('dragleave', function() {
    this.classList.remove('drag-over');
});

dropZone && dropZone.addEventListener('drop', function(e) {
    e.preventDefault();
    this.classList.remove('drag-over');
    
    if (e.dataTransfer.files.length) {
        const file = e.dataTransfer.files[0];
        
        // Проверяем, что это PDF
        if (file.type === 'application/pdf') {
            fileInput.files = e.dataTransfer.files;
            
            // Вызываем change событие вручную
            const event = new Event('change', { bubbles: true });
            fileInput.dispatchEvent(event);
        } else {
            alert('Будь ласка, завантажте файл у форматі PDF');
        }
    }
});

// Test page functionality
// Timer functionality for test page
function initializeTestPage() {
    const timerElement = document.getElementById('timer');
    const questions = document.querySelectorAll('.question');
    
    if (!timerElement || !questions.length) return;
    
    let timeLeft = 3600; // 1 hour in seconds
    let currentQuestion = 0;
    const totalQuestions = questions.length;
    
    function updateTimer() {
        const hours = Math.floor(timeLeft / 3600);
        const minutes = Math.floor((timeLeft % 3600) / 60);
        const seconds = timeLeft % 60;
        
        timerElement.textContent = `${String(hours).padStart(2, '0')}:${String(minutes).padStart(2, '0')}:${String(seconds).padStart(2, '0')}`;
        
        if (timeLeft <= 0) {
            clearInterval(timerInterval);
            document.getElementById('examForm').submit();
        }
        
        timeLeft--;
    }
    
    const timerInterval = setInterval(updateTimer, 1000);
    
    // Question navigation
    window.showQuestion = function(index) {
        questions.forEach(q => q.style.display = 'none');
        questions[index].style.display = 'block';
        
        // Show/hide navigation buttons
        document.getElementById('prev').style.display = index > 0 ? 'inline-block' : 'none';
        
        if (index === totalQuestions - 1) {
            document.getElementById('next').style.display = 'none';
            document.getElementById('finish').style.display = 'inline-block';
        } else {
            document.getElementById('next').style.display = 'inline-block';
            document.getElementById('finish').style.display = 'none';
        }
    }
    
    window.nextQuestion = function() {
        if (currentQuestion < totalQuestions - 1) {
            currentQuestion++;
            showQuestion(currentQuestion);
        }
    }
    
    window.prevQuestion = function() {
        if (currentQuestion > 0) {
            currentQuestion--;
            showQuestion(currentQuestion);
        }
    }
    
    // Initialize
    showQuestion(0);
    
    // Обработка выбора варианта ответа
    const checkboxes = document.querySelectorAll('.custom-checkbox');
    checkboxes.forEach(box => {
        box.addEventListener('click', () => {
            const groupName = box.querySelector('input').name;
            document.querySelectorAll(`input[name="${groupName}"]`).forEach(input => {
                input.closest('.custom-checkbox').classList.remove('selected');
            });
            box.classList.add('selected');
        });
    });
}

// Initialize test page when DOM is loaded
document.addEventListener('DOMContentLoaded', function() {
    initializeTestPage();
});

document.getElementById('ticket-id').textContent = Math.floor(Math.random() * 100) + 1;