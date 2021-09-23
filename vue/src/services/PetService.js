import axios from 'axios'

export default{ 
    createPet(pet) {
        return axios.post('/pet/addPuppy', pet);
    },
    listUserPets(userId) {
        return axios.get(`/pet/userPets/${userId}`);
    }
}