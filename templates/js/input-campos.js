var medioContactoIndex = 1;

const getNewCampoHtml = index => {
	return `<span class="mt-5">Campo ${index}</span>
        <label
            for="tipo-campo-${index}"
            class="text-left block mb-2 text-sm font-medium text-gray-900 dark:text-white"
            >Tipo de campo</label
        >
        <select
            id="tipo-campo-${index}"
            class="mb-5 bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
        >
            <option value="multiple-choice">
                Multiple choice
            </option>
            <option value="choice">Single Choice</option>
            <option value="texto-libre">Texto libre</option>
            <option value="numerico">Numerico</option>
            <option value="fecha">Fecha</option>
        </select>

        <label
            for="pregunta-${index}"
            class="text-left block mb-2 text-sm font-medium text-gray-900 dark:text-white"
            >Enunciado</label
        >
        <input
            type="text"
            id="pregunta-${index}"
            class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
            placeholder="Pregunta"
            required
        />

        <label
            for="obligatorio-campo-${index}"
            class="text-left block mb-2 text-sm font-medium text-gray-900 dark:text-white mt-5"
        >
            Obligatorio
            <input
                type="checkbox"
                id="obligatorio-campo-${index}"
                class="rounded-md p-2"
            />
        </label>`;
};

const nuevoCampo = () => {
	medioContactoIndex++;
	console.log(medioContactoIndex);
	let cont = document.getElementById("camposContainer");
	let newElement = getNewCampoHtml(medioContactoIndex);
	cont.innerHTML += newElement;
};
