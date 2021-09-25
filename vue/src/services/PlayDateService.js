import axios from 'axios'

export default{ 
    createPlayDate(playDate) {
        console.log(playDate);
        return axios.post('/pet/createPlayDate', playDate);
    },
    getCurrentDate(){
        return axios.get('/pet/createPlayDate');
    },
    getAllPlayDates(){
        return axios.get('/pet/allPlayDates/');
    },
    getAvailablePlayDates() {
        return axios.get('/pet/allPlayDates/available');
    }
}