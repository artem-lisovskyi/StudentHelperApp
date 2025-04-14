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

uploadTrigger.addEventListener('click', function(e) {
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

fileInput.addEventListener('change', function() {
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
uploadForm.addEventListener('submit', function() {
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

dropZone.addEventListener('dragover', function(e) {
    e.preventDefault();
    this.classList.add('drag-over');
});

dropZone.addEventListener('dragleave', function() {
    this.classList.remove('drag-over');
});

dropZone.addEventListener('drop', function(e) {
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