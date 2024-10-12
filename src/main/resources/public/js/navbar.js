document.addEventListener('DOMContentLoaded', (event) => {
    const dropdownToggles = document.querySelectorAll('[data-collapse-toggle]');
    const navItems = document.querySelectorAll('.nav-item');
    const currentPath = window.location.pathname;

    // Función para manejar los dropdowns
    function handleDropdowns() {
        dropdownToggles.forEach(toggle => {
            toggle.addEventListener('click', function (e) {
                const target = document.getElementById(this.getAttribute('aria-controls'));
                if (target) {
                    target.classList.toggle('hidden');
                    localStorage.setItem(this.getAttribute('aria-controls'), target.classList.contains('hidden') ? 'closed' : 'open');
                }
            });

            const targetId = toggle.getAttribute('aria-controls');
            const target = document.getElementById(targetId);
            if (target && localStorage.getItem(targetId) === 'open') {
                target.classList.remove('hidden');
            }
        });
    }

    // Función para resaltar el elemento de navegación activo
    function highlightActiveNavItem() {
        navItems.forEach(item => {
            const itemPath = item.getAttribute('href');
            if (itemPath === currentPath) {
                item.classList.add('azulIntermedio'); // Clase para resaltar el item activo

                // Si el item está dentro de un dropdown, abre ese dropdown
                const parentDropdown = item.closest('ul[id^="dropdown-"]');
                if (parentDropdown) {
                    parentDropdown.classList.remove('hidden');
                    const parentToggle = document.querySelector(`[aria-controls="${parentDropdown.id}"]`);
                    if (parentToggle) {
                        localStorage.setItem(parentDropdown.id, 'open');
                    }
                }
            } else {
                item.classList.remove('azulIntermedio');
            }
        });
    }

    // Prevenir que el dropdown se cierre al hacer clic en sus items
    navItems.forEach(item => {
        item.addEventListener('click', function (e) {
            e.stopPropagation();
        });
    });

    // Inicializar funciones
    handleDropdowns();
    highlightActiveNavItem();
});