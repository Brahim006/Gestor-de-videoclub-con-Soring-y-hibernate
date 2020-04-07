/*
 * Interfaz de usuario principal a través de la cual se acceden a los servicios.
 */
package com.domain.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.table.TableModel;

import com.domain.gui.utils.TableModelNoEditable;

/**
 *
 * @author Brahim
 */
public class Gui extends JFrame{
    
    private JTabbedPane pestanias;
    private JTextField tfBusquedaAlquiler, tfDiasAlquiler, tfBusquedaCliente,
            tfNombreCliente, tfBusquedaPelicula, tfTituloPelicula, 
            tfCopiasPelicula, tfBusquedaPromocion, tfDescripcionPromo,
            tfDescuentoPromo, tfBusquedaGenero, tfDescripcionGenero;
    private JComboBox<String> jcbFiltrosAlquiler, jcbClienteAlquiler,
            jcbPeliculaAlquiler, jcbPromocion, jcbFiltrosCliente, 
            jcbFiltrosPelicula, jcbGeneroPelicula, jcbFiltrosPromocion,
            jcbFiltrosGenero;
    private JTable jtAlquiler, jtCliente, jtPeliculasCliente, 
            jtPromocionesCliente, jtPelicula, jtClientesPelicula,
            jtPromocion, jtClientesPromocion, jtGenero, jtPeliculasGenero;
    private JButton bCrearAlquiler, bBorrarAlquiler, bModificarAlquiler,
            bCrearCliente, bBorrarCliente, bModificarCliente, bCrearPelicula,
            bBorrarPelicula, bModificarPelicula, bCrearPromocion, 
            bBorrarPromocion, bModificarPromocion, bCrearGenero, 
            bModificarGenero, bBorrarGenero;
    
    // Constanstes estáticas
    public static final int CANT_FILAS = 30, CARACTERES_TEXTFIELD = 25;
    public static final Cursor CURSOR_PULSAR = new Cursor(Cursor.HAND_CURSOR),
            CURSOR_SELECCIONAR = new Cursor(Cursor.CROSSHAIR_CURSOR);
    public static Border borde = BorderFactory
                            .createEtchedBorder(Color.lightGray, Color.black);
    public static final Dimension DIMENSION_BLOQUE = new Dimension(300, 150),
            DIMENSION_TEXTFIELD = new Dimension(100, 30); 
    public static final String COLUMNAS_TABLA_ALQUILER[] = {"Cliente",
            "Película", "Fecha", "Días", "Promoción"},
            COLUMNAS_TABLA_CLIENTE[] = {"Cliente", "Películas", "Promociones"},
            COLUMNAS_TABLA_PELICULA[] = {"Título", "Género", 
                "Cantidad de copias", "Copias alquiladas", 
                "Clientes que la alquilan"},
            COLUMNAS_TABLA_PROMOCION[] = {"Descripcion", "Descuento",
                "Clientes que la aplican"},
            COLUMNAS_TABLA_GENERO[] = {"Descripción", "Películas"};
    
    public Gui(){
        super("Gestor Videoclub");
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        initComponents();
        this.pack();
        this.setResizable(true);
        this.setLocationRelativeTo(null);
    }
    
    /**
     * Crea los componentes al iniciar la ventana.
     */
    private void initComponents(){
        pestanias = new JTabbedPane();
        pestanias.add("Alquileres", crearPestaniaAlquiler());
        pestanias.add("Clientes", crearPestaniaCliente());
        pestanias.add("Películas", crearPestaniaPeliculas());
        pestanias.add("Promociones", crearPestaniaPromocion());
        pestanias.add("Géneros", crearPestaniaGenero());
        this.add(pestanias);
    }
    
    // Crea la pestania destinada a las operaciones de alquiler
    private JPanel crearPestaniaAlquiler(){
        
        JPanel pestaniaAquiler = new JPanel(new BorderLayout());
        
        // Panel norte
        JPanel pNorte = new JPanel(new BorderLayout());
        
        TitledBorder bordeN = new TitledBorder(borde,"Alquileres");
        pNorte.setBorder(bordeN);
        JPanel busqueda = new JPanel(new FlowLayout(FlowLayout.CENTER));
        busqueda.add(new JLabel("Buscar:"));
        tfBusquedaAlquiler =  new JTextField(CARACTERES_TEXTFIELD);
        tfBusquedaAlquiler.setPreferredSize(DIMENSION_TEXTFIELD);
        tfBusquedaAlquiler.setToolTipText("Las fechas deben respetar el " + 
                                                        "formato dd/mm/aaaa");
        busqueda.add(tfBusquedaAlquiler);
        busqueda.add(new JLabel("Filtro:"));
        
        jcbFiltrosAlquiler = new JComboBox<>();
        jcbFiltrosAlquiler.addItem("--Todos--");
        jcbFiltrosAlquiler.addItem("Cliente");
        jcbFiltrosAlquiler.addItem("Película");
        jcbFiltrosAlquiler.addItem("Fecha");
        jcbFiltrosAlquiler.addItem("Días");
        jcbFiltrosAlquiler.addItem("Promoción");
        jcbFiltrosAlquiler.setCursor(CURSOR_PULSAR);
        busqueda.add(jcbFiltrosAlquiler);
        pNorte.add(busqueda, BorderLayout.NORTH);
        
        TableModel tmAlquiler = new TableModelNoEditable(new String[CANT_FILAS]
                [COLUMNAS_TABLA_ALQUILER.length],COLUMNAS_TABLA_ALQUILER);
        jtAlquiler = new JTable(tmAlquiler);
        jtAlquiler.setCursor(CURSOR_SELECCIONAR);
        JScrollPane scroll = new JScrollPane(jtAlquiler);
        pNorte.add(scroll, BorderLayout.CENTER);
        
        // Panel sur
        JPanel pSur = new JPanel(new FlowLayout());
        
        
        JPanel pInfo = new JPanel();
        pInfo.setLayout(new BoxLayout(pInfo, BoxLayout.Y_AXIS));
        pInfo.setBorder(borde);
        bCrearAlquiler = new JButton("Insertar");
        bCrearAlquiler.setAlignmentX(CENTER_ALIGNMENT);
        bCrearAlquiler.setCursor(CURSOR_PULSAR);
        bCrearAlquiler.setToolTipText("Inserta un alquiler en la base de datos");
        pInfo.add(bCrearAlquiler);
        jcbClienteAlquiler = new JComboBox<>();
        jcbClienteAlquiler.addItem("--Seleccionar cliente--");
        jcbClienteAlquiler.setCursor(CURSOR_PULSAR);
        pInfo.add(jcbClienteAlquiler);
        jcbPeliculaAlquiler = new JComboBox<>();
        jcbPeliculaAlquiler.addItem("--Seleccionar película--");
        jcbPeliculaAlquiler.setCursor(CURSOR_PULSAR);
        pInfo.add(jcbPeliculaAlquiler);
        jcbPromocion = new JComboBox<>();
        jcbPromocion.addItem("--Seleccionar promoción--");
        jcbPromocion.addItem("No aplica");
        jcbPromocion.setCursor(CURSOR_PULSAR);
        pInfo.add(jcbPromocion);
        tfDiasAlquiler = new JTextField(CARACTERES_TEXTFIELD);
        tfDiasAlquiler.setPreferredSize(DIMENSION_TEXTFIELD);
        tfDiasAlquiler.setToolTipText("Cantidad de días por los que se " +
                                      "efectúa el alquiler");
        pInfo.add(tfDiasAlquiler);
        pInfo.setPreferredSize(DIMENSION_BLOQUE);
        pSur.add(pInfo);
        
        JPanel pBorrarModificar = new JPanel();
        pBorrarModificar.setLayout(new BoxLayout(pBorrarModificar, 
                                                 BoxLayout.Y_AXIS));
        pBorrarModificar.setBorder(borde);
        bBorrarAlquiler = new JButton("Borrar");
        bBorrarAlquiler.setAlignmentX(CENTER_ALIGNMENT);
        bBorrarAlquiler.setCursor(CURSOR_PULSAR);
        bBorrarAlquiler.setToolTipText("Borra el alquiler seleccionado");
        pBorrarModificar.add(bBorrarAlquiler);
        pBorrarModificar.add(new JLabel("Seleccione una ó varias filas para " +
                                        "borrar."));
        bModificarAlquiler = new JButton("Modificar");
        bModificarAlquiler.setAlignmentX(CENTER_ALIGNMENT);
        bModificarAlquiler.setCursor(CURSOR_PULSAR);
        bModificarAlquiler.setToolTipText("Cambia las características del \n" +
                "cliete seleccionado");
        pBorrarModificar.add(bModificarAlquiler);
        pBorrarModificar.add(new JLabel("Seleccione una fila para modificar."));
        pBorrarModificar.setPreferredSize(DIMENSION_BLOQUE);
        pSur.add(pBorrarModificar);
        
        // Adición y retorno de los paneles
        pestaniaAquiler.add(pNorte, BorderLayout.CENTER);
        pestaniaAquiler.add(pSur, BorderLayout.SOUTH);
        
        return pestaniaAquiler;
        
    } // fin crearPestaniaAlquiler
    
    // Crea la pestaña para la gestión de clientes
    private JPanel crearPestaniaCliente(){
        
        JPanel pestaniaCliente = new JPanel(new BorderLayout());
        
        // Panel centro
        JPanel pNorte = new JPanel(new BorderLayout());
        
        TitledBorder bordeN = new TitledBorder(borde,"Clientes");
        pNorte.setBorder(bordeN);
        JPanel busqueda = new JPanel(new FlowLayout(FlowLayout.CENTER));
        busqueda.add(new JLabel("Buscar:"));
        tfBusquedaCliente =  new JTextField(CARACTERES_TEXTFIELD);
        tfBusquedaCliente.setPreferredSize(DIMENSION_TEXTFIELD);
        busqueda.add(tfBusquedaCliente);
        busqueda.add(new JLabel("Filtro:"));
        
        jcbFiltrosCliente = new JComboBox<>();
        jcbFiltrosCliente.addItem("--Todos--");
        jcbFiltrosCliente.addItem("Cliente");
        jcbFiltrosCliente.addItem("Películas alquiladas");
        jcbFiltrosCliente.addItem("Película");
        jcbFiltrosCliente.addItem("Promociones aplicadas");
        jcbFiltrosCliente.addItem("Promoción");
        jcbFiltrosCliente.setCursor(CURSOR_PULSAR);
        busqueda.add(jcbFiltrosCliente);
        pNorte.add(busqueda, BorderLayout.NORTH);
        
        TableModel tmCliente = new TableModelNoEditable
        (new String[30][COLUMNAS_TABLA_CLIENTE.length],COLUMNAS_TABLA_CLIENTE);
        jtCliente = new JTable(tmCliente);
        jtCliente.setCursor(CURSOR_SELECCIONAR);
        JScrollPane scroll = new JScrollPane(jtCliente);
        pNorte.add(scroll, BorderLayout.CENTER);
        
        // Panel Sur
        
        JPanel pSur = new JPanel(new FlowLayout());
        
        JPanel pModificar = new JPanel();
        pModificar.setLayout(new BoxLayout(pModificar, BoxLayout.Y_AXIS));
        pModificar.setBorder(borde);
        bCrearCliente = new JButton("Crear");
        bCrearCliente.setAlignmentX(CENTER_ALIGNMENT);
        bCrearCliente.setToolTipText("Inserta un cliente en la base de datos");
        bCrearCliente.setCursor(CURSOR_PULSAR);
        pModificar.add(bCrearCliente);
        tfNombreCliente = new JTextField(CARACTERES_TEXTFIELD);
        tfNombreCliente.setPreferredSize(DIMENSION_TEXTFIELD);
        tfNombreCliente
                    .setToolTipText("Nombre del cliente a insertar/modificar");
        pModificar.add(tfNombreCliente);
        bBorrarCliente = new JButton("Borrar");
        bBorrarCliente.setAlignmentX(CENTER_ALIGNMENT);
        bBorrarCliente.setToolTipText("Borra la fila seleccionada de la \n" +
                                      "base de datos");
        bBorrarCliente.setCursor(CURSOR_PULSAR);
        pModificar.add(bBorrarCliente);
        bModificarCliente = new JButton("Modificar");
        bModificarCliente.setAlignmentX(CENTER_ALIGNMENT);
        bModificarCliente.setToolTipText("Modifica la fila seleccionada en \n"
                                         + "la base de datos con los datos " +
                                           "ingresados");
        bModificarCliente.setCursor(CURSOR_PULSAR);
        pModificar.add(bModificarCliente);
        pModificar.setPreferredSize(DIMENSION_BLOQUE);
        pSur.add(pModificar);
        
        String nomTablaPelis[] = {"Películas alquiladas"};
        jtPeliculasCliente = new JTable(new TableModelNoEditable(nomTablaPelis, 
                                                                 CANT_FILAS));
        jtPeliculasCliente.setCursor(CURSOR_SELECCIONAR);
        JScrollPane scrollPeliClientes = new JScrollPane(jtPeliculasCliente);
        scrollPeliClientes.setPreferredSize(DIMENSION_BLOQUE);
        pSur.add(scrollPeliClientes);
        
        String nomTablaPromos[] = {"Promociones aplicadas"};
        jtPromocionesCliente = new JTable
                                    (new TableModelNoEditable(nomTablaPromos, 
                                                              CANT_FILAS));
        jtPromocionesCliente.setCursor(CURSOR_SELECCIONAR);
        JScrollPane scrollPromoClientes = new JScrollPane(jtPromocionesCliente);
        scrollPromoClientes.setPreferredSize(DIMENSION_BLOQUE);
        pSur.add(scrollPromoClientes);
        
        // Adición y retorno de los paneles
        pestaniaCliente.add(pNorte, BorderLayout.CENTER);
        pestaniaCliente.add(pSur, BorderLayout.SOUTH);
        
        return pestaniaCliente;
        
    } // fin crearPestaniaCliente
    
    // Crea la pestaña para gestionar películas
    private JPanel crearPestaniaPeliculas(){
        
        JPanel pestaniaPeliculas = new JPanel(new BorderLayout());
        
        // Panel centro
        JPanel pCentro = new JPanel();
        pCentro.setLayout(new BoxLayout(pCentro, BoxLayout.Y_AXIS));
        pCentro.setBorder(new TitledBorder(borde, "Películas"));
        
        JPanel pBusqueda = new JPanel(new FlowLayout());
        pBusqueda.add(new JLabel("Buscar:"));
        tfBusquedaPelicula = new JTextField(CARACTERES_TEXTFIELD);
        tfBusquedaPelicula.setPreferredSize(DIMENSION_TEXTFIELD);
        pBusqueda.add(tfBusquedaPelicula);
        pBusqueda.add(new JLabel("Filtros:"));
        jcbFiltrosPelicula = new JComboBox<>();
        jcbFiltrosPelicula.addItem("--Todos--");
        jcbFiltrosPelicula.addItem("Titulo");
        jcbFiltrosPelicula.addItem("Genero");
        jcbFiltrosPelicula.addItem("Cantidad de copias");
        jcbFiltrosPelicula.addItem("Copias alquiladas");
        jcbFiltrosPelicula.addItem("Cliente");
        jcbFiltrosPelicula.addItem("Cantidad de clientes");
        jcbFiltrosPelicula.setCursor(CURSOR_PULSAR);
        pBusqueda.add(jcbFiltrosPelicula);
        pCentro.add(pBusqueda);
        
        TableModel modeloPelicula = new TableModelNoEditable(
        new String[30][COLUMNAS_TABLA_PELICULA.length], COLUMNAS_TABLA_PELICULA
        );
        jtPelicula = new JTable(modeloPelicula);
        jtPelicula.setCursor(CURSOR_SELECCIONAR);
        JScrollPane scroll = new JScrollPane(jtPelicula);
        pCentro.add(scroll);
        
        // Panel sur
        JPanel pSur = new JPanel(new FlowLayout());
        
        JPanel pCrear = new JPanel();
        pCrear.setLayout(new BoxLayout(pCrear, BoxLayout.Y_AXIS));
        pCrear.setBorder(borde);
        bCrearPelicula = new JButton("Insertar");
        bCrearPelicula.setAlignmentX(CENTER_ALIGNMENT);
        bCrearPelicula.setCursor(CURSOR_PULSAR);
        bCrearPelicula.setToolTipText("Inserta una palícula en la base de datos");
        pCrear.add(bCrearPelicula);
        tfTituloPelicula = new JTextField(25);
        tfTituloPelicula.setToolTipText("Título de la película");
        pCrear.add(tfTituloPelicula);
        jcbGeneroPelicula = new JComboBox<>();
        jcbGeneroPelicula.addItem("--Seleccione género--");
        jcbGeneroPelicula.addItem("No definido");
        jcbGeneroPelicula.setCursor(CURSOR_PULSAR);
        pCrear.add(jcbGeneroPelicula);
        tfCopiasPelicula = new JTextField(25);
        tfCopiasPelicula.setToolTipText("Cantidad de copias en stock");
        pCrear.add(tfCopiasPelicula);
        pCrear.setPreferredSize(DIMENSION_BLOQUE);
        pSur.add(pCrear);
        
        JPanel pGestionar = new JPanel();
        pGestionar.setLayout(new BoxLayout(pGestionar, BoxLayout.Y_AXIS));
        bBorrarPelicula = new JButton("Borrar");
        bBorrarPelicula.setAlignmentX(CENTER_ALIGNMENT);
        bBorrarPelicula.setCursor(CURSOR_PULSAR);
        bBorrarPelicula.setToolTipText("Borra la película seleccionada");
        pGestionar.add(bBorrarPelicula);
        bModificarPelicula = new JButton("Modificar");
        bModificarPelicula.setAlignmentX(CENTER_ALIGNMENT);
        bModificarPelicula.setCursor(CURSOR_PULSAR);
        bModificarAlquiler.setToolTipText("Modifica la película seleccionada");
        pGestionar.add(bModificarPelicula);
        pGestionar.setPreferredSize(DIMENSION_BLOQUE);
        pSur.add(pGestionar);
        
        String nombTablaClientes[] = {"Clientes alquilándola:"};
        jtClientesPelicula = new JTable(new TableModelNoEditable
                                                       (nombTablaClientes, 20));
        jtClientesPelicula.setCursor(CURSOR_SELECCIONAR);
        JScrollPane scrollClientes = new JScrollPane(jtClientesPelicula);
        scrollClientes.setPreferredSize(DIMENSION_BLOQUE);
        pSur.add(scrollClientes);
        
        // Adición de los paneles y retorno
        pestaniaPeliculas.add(pCentro, BorderLayout.CENTER);
        pestaniaPeliculas.add(pSur, BorderLayout.SOUTH);
        
        return pestaniaPeliculas;
        
    } // fin crearPestaniaPeliculas
    
    // Crea la pestaña para gestionar las promociones aplicadas
    private JPanel crearPestaniaPromocion(){
        
        JPanel pestaniaPromocion = new JPanel(new BorderLayout());
        
        // Panel centro
        JPanel pCentro = new JPanel();
        pCentro.setLayout(new BoxLayout(pCentro, BoxLayout.Y_AXIS));
        pCentro.setBorder(new TitledBorder(borde, "Promociones"));
        
        JPanel pBusqueda = new JPanel(new FlowLayout());
        pBusqueda.add(new JLabel("Buscar:"));
        tfBusquedaPromocion = new JTextField(CARACTERES_TEXTFIELD);
        tfBusquedaPromocion.setPreferredSize(DIMENSION_TEXTFIELD);
        pBusqueda.add(tfBusquedaPromocion);
        pBusqueda.add(new JLabel("Filtros:"));
        jcbFiltrosPromocion = new JComboBox<>();
        jcbFiltrosPromocion.addItem("--Todos--");
        jcbFiltrosPromocion.addItem("Descripción");
        jcbFiltrosPromocion.addItem("Descuento");
        jcbFiltrosPromocion.addItem("Cliente");
        jcbFiltrosPromocion.addItem("Cantidad de clientes");
        jcbFiltrosPromocion.setCursor(CURSOR_PULSAR);
        pBusqueda.add(jcbFiltrosPromocion);
        pCentro.add(pBusqueda);
        
        TableModel modeloPromo = new TableModelNoEditable
                            (new String[30][COLUMNAS_TABLA_PROMOCION.length], 
                            COLUMNAS_TABLA_PROMOCION);
        jtPromocion = new JTable(modeloPromo);
        jtPromocion.setCursor(CURSOR_SELECCIONAR);
        JScrollPane scroll = new JScrollPane(jtPromocion);
        pCentro.add(scroll);
        
        // Panel sur
        JPanel pSur = new JPanel(new FlowLayout());
        
        JPanel pBotones = new JPanel();
        pBotones.setLayout(new BoxLayout(pBotones, BoxLayout.Y_AXIS));
        bCrearPromocion= new JButton("Insertar");
        bCrearPromocion.setAlignmentX(CENTER_ALIGNMENT);
        bCrearPromocion.setCursor(CURSOR_PULSAR);
        bCrearPromocion.setToolTipText("Inserta una promoción en la base de datos");
        pBotones.add(bCrearPromocion);
        bModificarPromocion = new JButton("Modificar");
        bModificarPromocion.setAlignmentX(CENTER_ALIGNMENT);
        bModificarPromocion.setCursor(CURSOR_PULSAR);
        bModificarPromocion.setToolTipText("Modifica la promocón seleccionada");
        pBotones.add(bModificarPromocion);
        bBorrarPromocion = new JButton("Borrar");
        bBorrarPromocion.setAlignmentX(CENTER_ALIGNMENT);
        bBorrarPromocion.setCursor(CURSOR_PULSAR);
        bBorrarPromocion.setToolTipText("Borra la promoción seleccionada");
        pBotones.add(bBorrarPromocion);
        pBotones.setPreferredSize(DIMENSION_BLOQUE);
        pSur.add(pBotones);
        
        JPanel pInput = new JPanel();
        pInput.setLayout(new BoxLayout(pInput, BoxLayout.Y_AXIS));
        tfDescripcionPromo = new JTextField(CARACTERES_TEXTFIELD);
        tfDescripcionPromo.setToolTipText("Descripción de la promoción");
        pInput.add(tfDescripcionPromo);
        tfDescuentoPromo = new JTextField(CARACTERES_TEXTFIELD);
        tfDescuentoPromo.setToolTipText("Descuento neto");
        pInput.add(tfDescuentoPromo);
        pInput.setPreferredSize(DIMENSION_BLOQUE);
        pSur.add(pInput);
        
        String[] colClientes = {"Cliente"};
        TableModel modeloCliente = new TableModelNoEditable(colClientes, 
                                                            CANT_FILAS);
        jtClientesPromocion = new JTable(modeloCliente);
        jtClientesPromocion.setCursor(CURSOR_SELECCIONAR);
        jtClientesPromocion.setToolTipText("Clientes que la aplican");
        JScrollPane scrollClientes = new JScrollPane(jtClientesPromocion);
        scrollClientes.setPreferredSize(DIMENSION_BLOQUE);
        pSur.add(scrollClientes);
        
        // Adición y retorno
        pestaniaPromocion.add(pCentro, BorderLayout.CENTER);
        pestaniaPromocion.add(pSur, BorderLayout.SOUTH);
        
        return pestaniaPromocion;
        
    } // finCrearPestaniaPromocion
    
    // Crea la pestania para gestionar el género de las películas
    private JPanel crearPestaniaGenero(){
    
        JPanel pestaniaGenero = new JPanel(new BorderLayout());
        pestaniaGenero.setBorder(new TitledBorder(borde, "Géneros"));
        
        // Panel norte
        JPanel pBusqueda = new JPanel(new FlowLayout());
        pBusqueda.add(new JLabel("Buscar:"));
        tfBusquedaGenero = new JTextField(CARACTERES_TEXTFIELD);
        tfBusquedaGenero.setPreferredSize(DIMENSION_TEXTFIELD);
        pBusqueda.add(tfBusquedaGenero);
        pBusqueda.add(new JLabel("Filtros:"));
        jcbFiltrosGenero = new JComboBox<>();
        jcbFiltrosGenero.addItem("--Todos--");
        jcbFiltrosGenero.addItem("Descripción");
        jcbFiltrosGenero.addItem("Película");
        jcbFiltrosGenero.addItem("Cantidad películas");
        jcbFiltrosGenero.setCursor(CURSOR_PULSAR);
        pBusqueda.add(jcbFiltrosGenero);
        
        // Panel centro
        JPanel pCentro = new JPanel(new FlowLayout());
        
        TableModel modeloGenero = new TableModelNoEditable(COLUMNAS_TABLA_GENERO
                                                           ,CANT_FILAS);
        jtGenero = new JTable(modeloGenero);
        jtGenero.setCursor(CURSOR_SELECCIONAR);
        JScrollPane scroll = new JScrollPane(jtGenero);
        pCentro.add(scroll);
        
        JPanel pOeste = new JPanel();
        pOeste.setLayout(new BoxLayout(pOeste, BoxLayout.Y_AXIS));
        tfDescripcionGenero = new JTextField(CARACTERES_TEXTFIELD);
        tfDescripcionGenero.setToolTipText("Descripción del género");
        pOeste.add(tfDescripcionGenero);
        bCrearGenero = new JButton("Insertar");
        bCrearGenero.setAlignmentX(CENTER_ALIGNMENT);
        bCrearGenero.setCursor(CURSOR_PULSAR);
        bCrearGenero.setToolTipText("Inserta un género en la base de datos");
        pOeste.add(bCrearGenero);
        bModificarGenero = new JButton("Modificar");
        bModificarGenero.setAlignmentX(CENTER_ALIGNMENT);
        bModificarGenero.setCursor(CURSOR_PULSAR);
        bModificarGenero.setToolTipText("Modifica el género seleccionado");
        pOeste.add(bModificarGenero);
        bBorrarGenero = new JButton("Borrar");
        bBorrarGenero.setAlignmentX(CENTER_ALIGNMENT);
        bBorrarGenero.setCursor(CURSOR_PULSAR);
        bBorrarGenero.setToolTipText("Borra el género seleccionado");
        pOeste.add(bBorrarGenero);
        
        String[] colsPeliculas = {"Título", "Copias"};
        TableModel modeloPeliculas = new TableModelNoEditable(colsPeliculas, 
                                                              CANT_FILAS);
        jtPeliculasGenero = new JTable(modeloPeliculas);
        jtPeliculasGenero.setCursor(CURSOR_SELECCIONAR);
        jtPeliculasGenero.setToolTipText("Películas pertenecientes");
        JScrollPane scrollPeliculas = new JScrollPane(jtPeliculasGenero);
        scrollPeliculas.setPreferredSize(DIMENSION_BLOQUE);
        pOeste.add(scrollPeliculas);
        pCentro.add(pOeste);
        
        // Adición y retorno
        pestaniaGenero.add(pBusqueda, BorderLayout.NORTH);
        pestaniaGenero.add(pCentro, BorderLayout.CENTER);
        
        return pestaniaGenero;
        
    } // fin crearPestaniaGenero
    
}
