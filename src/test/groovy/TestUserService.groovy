import com.example.ejercicio.domain.Phone
import com.example.ejercicio.domain.User
import com.example.ejercicio.dto.UserResponseDTO
import com.example.ejercicio.exception.ExistingMailException
import com.example.ejercicio.mapper.UserMapper
import com.example.ejercicio.repository.UserPhoneRepository
import com.example.ejercicio.repository.UserRepository
import com.example.ejercicio.service.UserService
import org.springframework.security.crypto.password.PasswordEncoder
import spock.lang.Specification

class TestUserService extends Specification {

    UserService userService
    UserRepository userRepository
    UserPhoneRepository userPhoneRepository
    UserMapper userMapper
    PasswordEncoder passwordEncoder
    User user
    UserResponseDTO userResponseDTO

    void setup() {
        user = new User(1, "ignacio", "P4ssw0rd", "ignachobarberis@gmail.com", new ArrayList<Phone>(),
                null, null, null,
                "", false)
        userResponseDTO = new UserResponseDTO(1, null, null, null, "", true)
        // Stubbing
        userRepository = Stub(UserRepository) {
            findById(_) >> {Optional.of(user)}
            save(_) >> null
        }
        userPhoneRepository = Stub(UserPhoneRepository)
        userMapper = Stub(UserMapper) {
            fromUserTOUserResponseDTO(_) >> {userResponseDTO}
        }
        passwordEncoder = Stub(PasswordEncoder)

        userService = new UserService(userRepository, userPhoneRepository, userMapper, passwordEncoder)
    }

    void 'Agregar usuario OK'() {
        given: 'un usuario'
            userRepository.findByEmail(_) >> null

        when: "lo agregamos"
            userService.addUser(user)

        then: "no se arroja ninguna Exception"
            noExceptionThrown()
    }

    void 'Agregar usuario NOK(mail existente)'() {
        given: 'un usuario'
            userRepository.findByEmail(_) >> user

        when: "lo agregamos"
            userService.addUser(user)

        then: "como existe el correo se devuelve una ExistingMailException"
            thrown ExistingMailException
    }

    void 'Eliminar usuario'() {
        given: 'un id de usuario'
            def idUser = 2

        when: "lo eliminamos"
            userService.delUser(idUser)

        then: "no se arroja ninguna Exception"
            noExceptionThrown()
    }

    void 'Actualizar usuario'() {
        given: 'un usuario'

        when: "lo actualizamos"
            userService.updUser(user)

        then: "no se arroja ninguna Exception"
            noExceptionThrown()
    }

}



