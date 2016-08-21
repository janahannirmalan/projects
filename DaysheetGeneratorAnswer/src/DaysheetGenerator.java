import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import org.yaml.snakeyaml.*;
import org.yaml.snakeyaml.constructor.Constructor;

public class DaysheetGenerator {
	
	public static void main(String[] args) {
		//Creates a string with the file name
		String fileName = "resources/daysheet.yml";
		
		Constructor appointmentConstructor = new Constructor(Appointments.class);
		TypeDescription appointmentDescription = new TypeDescription(Appointment.class);
		appointmentDescription.putMapPropertyType("appointments", Appointment.class, Object.class);
		appointmentConstructor.addTypeDescription(appointmentDescription);
		//Creates a new yaml daysheet 
		Yaml daysheet = new Yaml(appointmentConstructor);
		
		try{
			InputStream ios = new FileInputStream(new File(fileName));
			Appointments listOfAppointments = (Appointments)daysheet.load(ios);
			
			Collections.sort(listOfAppointments.getAppointments());
			
			for(Appointment appointment : listOfAppointments.getAppointments()){
				System.out.println(appointment.toString());
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}