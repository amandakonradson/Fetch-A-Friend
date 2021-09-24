import axios from 'axios'

export default{ 
    createPlayDate(playDate) {
        return axios.post('/pet/createPlayDate', playDate);
    },
    getCurrentDate(){
        return axios.get('/pet/createPlayDate');
    },
    createPlayDateLocation(location) {
        return axios.post('/pet/createPlayDate/location', location);
    },
}