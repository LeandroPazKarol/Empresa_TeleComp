package factory;

public class FabricaReclamos {

    public static ReclamoFactory getFactory(String tipo) {
        switch (tipo) {
            case "Técnico": return new ReclamoTecnicoFactory();
            case "Facturación": return new ReclamoFacturacionFactory();
            default: return new ReclamoServicioFactory();
        }
    }
}
