document.addEventListener('DOMContentLoaded', function () {
    const dropzoneFile = document.getElementById('dropzone-file');
    const imagePreview = document.getElementById('image-preview');
    const preview = document.getElementById('preview');

    dropzoneFile.addEventListener('change', function (e) {
        const file = e.target.files[0];
        if (file) {
            const reader = new FileReader();

            reader.onload = function (e) {
                preview.src = e.target.result;
                imagePreview.classList.remove('hidden');
            }

            reader.readAsDataURL(file);
        }
    });

    // Opcional: Añadir funcionalidad de arrastrar y soltar
    const dropzone = document.querySelector('label[for="dropzone-file"]');

    ['dragenter', 'dragover', 'dragleave', 'drop'].forEach(eventName => {
        dropzone.addEventListener(eventName, preventDefaults, false);
    });

    function preventDefaults(e) {
        e.preventDefault();
        e.stopPropagation();
    }

    ['dragenter', 'dragover'].forEach(eventName => {
        dropzone.addEventListener(eventName, highlight, false);
    });

    ['dragleave', 'drop'].forEach(eventName => {
        dropzone.addEventListener(eventName, unhighlight, false);
    });

    function highlight(e) {
        dropzone.classList.add('border-blue-500', 'border-4');
    }

    function unhighlight(e) {
        dropzone.classList.remove('border-blue-500', 'border-4');
    }

    dropzone.addEventListener('drop', handleDrop, false);

    function handleDrop(e) {
        const dt = e.dataTransfer;
        const file = dt.files[0];

        dropzoneFile.files = dt.files;

        if (file) {
            const reader = new FileReader();

            reader.onload = function (e) {
                preview.src = e.target.result;
                imagePreview.classList.remove('hidden');
            }

            reader.readAsDataURL(file);
        }
    }
});