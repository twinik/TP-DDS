var medioContactoIndex = 1;

const getCanalLabel = index => {
    return `<label for="medio-contacto-${index}" class="text-left block mb-2 text-sm font-medium text-gray-900 dark:text-white">Canal de Contacto ${index}</label>`;
};

const getSelectTemplate = index => {
    return `<select id="medio-contacto-${index}" class="mb-5 bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"> <option value="Whatsapp">Whatsapp</option><option value="Mail">Mail</option><option value="Telegram">Telegram</option> </select>`;
};

const getContactoLabelTemplate = index => {
    return `<label for="contacto-${index}" class="text-left block mb-2 text-sm font-medium text-gray-900 dark:text-white">Contacto ${index}</label>`;
};

const getInputLabelTemplate = index => {
    return `<input type="number" id="contacto-${index}" aria-describedby="helper-text-explanation" class="mb-5 bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500" placeholder="Nro de telefono o Email" required min="1"/>`;
};

const nuevoMedioContacto = () => {
    medioContactoIndex++;
    console.log(medioContactoIndex);
    let cont = document.getElementById("mediosContactoContainer");
    let newElement =
        getCanalLabel(medioContactoIndex) +
        getSelectTemplate(medioContactoIndex) +
        getContactoLabelTemplate(medioContactoIndex) +
        getInputLabelTemplate(medioContactoIndex);
    cont.innerHTML += newElement;
};
