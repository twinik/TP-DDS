var medioContactoIndex = 1;

const getCanalLabel = index => {
    return `<label for="canal-contacto-${index}" class="block text-sm font-medium text-gray-300 mb-1">Canal de Contacto ${index}</label>`;
};

const getSelectTemplate = index => {
    return `<select id="canal-contacto-${index}" name="canal-${index}" class="mt-1 mb-4 block w-full px-3 py-2 bg-gray-700 border border-gray-600 rounded-md text-white focus:outline-none focus:ring-2 focus:ring-blue-500 focus:border-transparent">
        <option value="Whatsapp">Whatsapp</option>
        <option value="Mail">Mail</option>
        <option value="Telegram">Telegram</option>
    </select>`;
};

const getContactoLabelTemplate = index => {
    return `<label for="contacto-${index}" class="block text-sm font-medium text-gray-300 mb-1">Contacto ${index}</label>`;
};

const getInputLabelTemplate = index => {
    return `<input type="text" id="contacto-${index}" name="contacto-${index}" aria-describedby="helper-text-explanation" class="mt-1 mb-4 block w-full px-3 py-2 bg-gray-700 border border-gray-600 rounded-md text-white placeholder-gray-400 focus:outline-none focus:ring-2 focus:ring-blue-500 focus:border-transparent" placeholder="Nro de teléfono o Email" required min="1"/>`;
};

const nuevoMedioContacto = () => {
    medioContactoIndex++;
    console.log(medioContactoIndex);

    let cont = document.getElementById("mediosContactoContainer");

    let newElement = document.createElement("div");
    newElement.className = "space-y-4";
    newElement.innerHTML =
        getCanalLabel(medioContactoIndex) +
        getSelectTemplate(medioContactoIndex) +
        getContactoLabelTemplate(medioContactoIndex) +
        getInputLabelTemplate(medioContactoIndex);

    const cantContactos = document.getElementById("cantidad-contactos");
    cantContactos.value = medioContactoIndex;

    cont.appendChild(newElement);
};