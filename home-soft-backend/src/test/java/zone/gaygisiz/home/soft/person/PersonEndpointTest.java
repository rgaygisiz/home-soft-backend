/*package zone.gaygisiz.home.soft.person;


import java.util.Collections;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;
import zone.gaygisiz.home.soft.web.ApplicationResponse;

@RunWith(MockitoJUnitRunner.class)
public class PersonEndpointTest {

  @Mock
  private PersonRepository personRepository;

  @InjectMocks
  private PersonEndpoint personEndpoint;

  @Test
  public void loadAllPersonen(){
    //GIVEN
    Mockito.when(personRepository.findAll()).thenReturn(Collections.emptyList());

    //WHEN
    ResponseEntity<ApplicationResponse<List<Person>>> response = personEndpoint
      .loadPersonen(Collections.EMPTY_MAP);

    //THEN
    Mockito.verify(personRepository).findAll();
    Assert.assertNotNull(response);
    Assert.assertNotNull(response.getBody());
    Assert.assertNotNull(response.getBody().getData());
    Assert.assertEquals(response.getBody().getData().size(), 0);

  }

}
*/
