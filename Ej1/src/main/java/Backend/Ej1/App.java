package Backend.Ej1;

import Backend.Ej1.controller.ClienteController;
import Backend.Ej1.model.service.ClienteServ;
import Backend.Ej1.view.VentanaBuscar;
import Backend.Ej1.view.VentanaPrincipal;
import Backend.Ej1.view.VentanaRegistro;

public class App 
{
	ClienteServ miclienteServ;
	VentanaPrincipal miVentanaPrincipal;
	VentanaBuscar miVentanaBuscar;
	VentanaRegistro miVentanaRegistro;
	ClienteController clienteController;

	public static void main(String[] args) 
	{
		App miPrincipal=new App();
		miPrincipal.iniciar();
	}

	/**
	 * Permite instanciar todas las clases con las que trabaja
	 * el sistema
	 */
	private void iniciar() {
		/*Se instancian las clases*/
		miVentanaPrincipal = new VentanaPrincipal();
		miVentanaRegistro = new VentanaRegistro();
		miVentanaBuscar = new VentanaBuscar();
		miclienteServ = new ClienteServ();
		clienteController = new ClienteController();
		
		/*Se establecen las relaciones entre clases*/
		miVentanaPrincipal.setCoordinador(clienteController);
		miVentanaRegistro.setCoordinador(clienteController);
		miVentanaBuscar.setCoordinador(clienteController);
		miclienteServ.setclienteController(clienteController);
		
		/*Se establecen relaciones con la clase coordinador*/
		clienteController.setMiVentanaPrincipal(miVentanaPrincipal);
		clienteController.setMiVentanaRegistro(miVentanaRegistro);
		clienteController.setMiVentanaBuscar(miVentanaBuscar);
		clienteController.setClienteServ(miclienteServ);
				
		miVentanaPrincipal.setVisible(true);
	}

}
