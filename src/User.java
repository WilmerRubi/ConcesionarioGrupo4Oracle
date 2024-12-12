public class User {

        private String username;
        private String password;
        private String tipoUsuario;
        private int idUsuario;
        private int id;
    
        public User(String username, String password, String tipoUsuario) {
            this.username = username;
            this.password = password;
            this.tipoUsuario = tipoUsuario;
        }
    
        public int getId() {
            return id;
        }

        public void setId(int idUsuario) {
            this.idUsuario = idUsuario;
        }
    
        public String getUsername() {
            return username;
        }
    
        public String getPassword() {
            return password;
        }
    
        public String getTipoUsuario() {
            return tipoUsuario;
        }
    
        public int getIdUsuario() {
            return idUsuario;
        }
}
