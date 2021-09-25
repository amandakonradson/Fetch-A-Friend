import axios from 'axios'

export default{ 
    createPlayDate(playDate) {
        return axios.post('/pet/createPlayDate', playDate);
    },
    getCurrentDate(){
        return axios.get('/pet/createPlayDate');
    },
   
}