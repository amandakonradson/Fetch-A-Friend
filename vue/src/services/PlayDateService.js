import axios from 'axios'

export default{ 
    createPlayDate(playDate) {
        return axios.post('/pet/createPlayDate', playDate);
    }
}