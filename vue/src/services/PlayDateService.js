import axios from 'axios'

export default{ 
    createPlayDate(playDate, location) {
        return axios.post('/pet/createPlayDate', playDate, location);
    },
    getCurrentDate(){
        return axios.get('/pet/createPlayDate');
    }
}