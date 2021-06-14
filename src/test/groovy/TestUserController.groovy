import com.example.ejercicio.controller.UserController
import com.example.ejercicio.service.UserService
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import spock.lang.Subject

import javax.servlet.http.HttpServletResponse

import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*

class TestUserController extends spock.lang.Specification{
    // Mocking services
    def userService = Mock(UserService)

    // Init controller with mock:
    def @Subject controller = new UserController(userService)

    // Let Spring MVC Test process the controller:
    def mockMvc =  MockMvcBuilders.standaloneSetup(controller).build()
    void 'should call rest controller'() {
        when:
        System.out.println(mockMvc.getProperties())
        //def resultRestCall = mockMvc.perform(getAt("/users/all"))

        def resultRestCall = this.mockMvc.perform(get("/users/all")).andDo(print())
                .andExpect(view().name("index"));

        then:
        resultRestCall.andExpect(status().isOk())

        // Expect 1 call to repository to get users:
        //1 * customerRepository.findOne(_) >> new Customer(id:1, name:"Fatima", lastName: "Casau")
        //resultRestCall.andExpect(status().isOk())
            //    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        //    .andExpect(content().string('{"id":1,"name":"Fatima","lastName":"Casau"')) // This fails
    }

    /*void 'should call rest controller'() {
        when:
        when:
        def response = mockMvc
                .perform(get('/api/lookups/')
                .param()
                .andReturn()
                .response

        then:
        response.status == HttpServletResponse.SC_OK;
    }*/
}
