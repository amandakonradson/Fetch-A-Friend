import axios from 'axios'

export default{ 
    createPet(pet) {
        return axios.post('/pet/addPuppy', pet);
    },
    listUserPets(id) {
        return axios.get(`/pet/userPets/${id}`);
    }
}