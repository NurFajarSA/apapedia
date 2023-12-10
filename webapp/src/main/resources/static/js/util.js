function submitCatalog() {
    const form = document.getElementById('catalogForm');
    const formData = new FormData(form);

    axios.post('http://localhost:8083/api/catalog/create', formData, {
        headers: {
            'Content-Type': 'multipart/form-data',
        },
    })
    .then((response) => {
        // Handle success
        console.log('Catalog created successfully:', response.data);
        // Perform any other actions you need on success
    })
    .catch((error) => {
        // Handle error
        console.error('Error creating catalog:', error.response.data);
        // Perform any other actions you need on error
    });
}