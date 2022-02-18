package Backend.Ej3;

import Backend.Ej3.controller.*;
import Backend.Ej3.model.service.*;
import Backend.Ej3.view.*;

public class App 
{
	CientificosServ micientificosServ;
	ProyectoServ miproyectoServ;
	Asignado_AServ miAsignado_AServ;
	VentanaPrincipal miVentanaPrincipal;
	VentanaBuscarCientificos miVentanaBuscarCientificos;
	VentanaBuscarProyecto miVentanaBuscarProyecto;
	VentanaRegistroCientificos miVentanaRegistroCientificos;
	VentanaRegistroProyecto miVentanaRegistroProyecto;
	VentanaAsignar_a miVentanaAsignar_a;
	CientificosController cientificosController;
	ProyectoController proyectoController;
	Asignado_AController asignado_aController;

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
		miVentanaRegistroCientificos = new VentanaRegistroCientificos();
		miVentanaRegistroProyecto = new VentanaRegistroProyecto();
		miVentanaBuscarCientificos = new VentanaBuscarCientificos();
		miVentanaBuscarProyecto = new VentanaBuscarProyecto();
		miVentanaAsignar_a = new VentanaAsignar_a();
		micientificosServ = new CientificosServ();
		miproyectoServ = new ProyectoServ();
		miAsignado_AServ = new Asignado_AServ();
		cientificosController = new CientificosController();
		proyectoController = new ProyectoController();
		asignado_aController = new Asignado_AController();
		
		/*Se establecen las relaciones entre clases*/
		miVentanaPrincipal.setCoordinadores(cientificosController, proyectoController, asignado_aController);
		miVentanaRegistroCientificos.setCoordinador(cientificosController);
		miVentanaBuscarCientificos.setCoordinador(cientificosController);
		micientificosServ.setcientificosController(cientificosController);
		miVentanaRegistroProyecto.setCoordinador(proyectoController);
		miVentanaBuscarProyecto.setCoordinador(proyectoController);
		miproyectoServ.setproyectoController(proyectoController);
		miAsignado_AServ.setasignado_aController(asignado_aController);
		miVentanaAsignar_a.setCoordinador(asignado_aController);
		
		/*Se establecen relaciones con la clase coordinador*/
		cientificosController.setMiVentanaPrincipal(miVentanaPrincipal);
		cientificosController.setMiVentanaRegistroCientificos(miVentanaRegistroCientificos);
		cientificosController.setMiVentanaBuscarCientificos(miVentanaBuscarCientificos);
		cientificosController.setCientificosServ(micientificosServ);
		cientificosController.setAsignado_AController(asignado_aController);
		
		proyectoController.setMiVentanaPrincipal(miVentanaPrincipal);
		proyectoController.setMiVentanaRegistroProyecto(miVentanaRegistroProyecto);
		proyectoController.setMiVentanaBuscarProyecto(miVentanaBuscarProyecto);
		proyectoController.setProyectoServ(miproyectoServ);
		proyectoController.setAsignado_AController(asignado_aController);
		
		asignado_aController.setAsignado_aServ(miAsignado_AServ);
		asignado_aController.setMiVentanaAsignar_A(miVentanaAsignar_a);
		asignado_aController.setMiVentanaPrincipal(miVentanaPrincipal);
				
		miVentanaPrincipal.setVisible(true);
	}

}
