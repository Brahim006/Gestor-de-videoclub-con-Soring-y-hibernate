/*
 * Interfaz de usuario principal a través de la cual se acceden a los servicios.
 */
package com.domain.gui;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.table.TableModel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.sql.Date;
import java.util.StringTokenizer;
import java.util.Collection;
import java.util.ArrayList;

import com.domain.gui.utils.TableModelNoEditable;
import com.domain.gui.utils.InputVerifier;
import com.domain.Facade.Facade;
import com.domain.Facade.FacadeImple;
import com.domain.hibernate.DTO.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author Brahim
 */
public class Gui extends JFrame{
    
    private Facade facade; // Facade para acceso a datos
    private BotonListener botonListener;
    private TextFieldListener textfieldListener;
    private TableListener tableListener;
    
    private JTabbedPane pestanias;
    private JTextField tfBusquedaAlquiler, tfDiasAlquiler, tfBusquedaCliente,
            tfNombreCliente, tfBusquedaPelicula, tfTituloPelicula, 
            tfCopiasPelicula, tfBusquedaPromocion, tfDescripcionPromo,
            tfDescuentoPromo, tfBusquedaGenero, tfDescripcionGenero;
    private JComboBox<String> jcbFiltrosAlquiler, jcbClienteAlquiler,
            jcbPeliculaAlquiler, jcbPromocionAlquiler, jcbFiltrosCliente, 
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
                "Cantidad de copias", "Clientes que la alquilan"},
            COLUMNAS_TABLA_PROMOCION[] = {"Descripcion", "Descuento",
                "Clientes que la aplican"},
            COLUMNAS_TABLA_GENERO[] = {"Descripción", "Películas"};
    
    public Gui(){
        super("Gestor Videoclub");
        
        ApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");
        
        facade = (Facade)ctx.getBean("FACADE");
        botonListener = new BotonListener();
        textfieldListener = new TextFieldListener();
        tableListener = new TableListener();
        
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        initComponents();
        this.setResizable(false);
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
        
        try { // se inicializan todos los recurso con la información de la bd
            
            llenarTablaAlquiler(facade.obtenerAlquiler());
            llenarTablaCliente(facade.obtenerCliente());
            llenarTablaPelicula(facade.obtenerPelicula());
            llenarTablaPromocion(facade.obtenerPromocion());
            llenarTablaGenero(facade.obtenerGenero());
            
            llenarComboBox(0);
            llenarComboBox(1);
            llenarComboBox(2);
            llenarComboBox(3);
            
        } catch (SQLException sqle) {
            JOptionPane.showMessageDialog(null, "Problema al consultar la base "
                    + "de datos", "Error de datos", JOptionPane.ERROR_MESSAGE);
        }
        
        this.pack();
        
    } // fin initComponents
    
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
        tfBusquedaAlquiler.addKeyListener(textfieldListener);
        busqueda.add(tfBusquedaAlquiler);
        busqueda.add(new JLabel("Filtro:"));
        
        jcbFiltrosAlquiler = new JComboBox<>();
        jcbFiltrosAlquiler.addItem("--Todos--");
        jcbFiltrosAlquiler.addItem("Cliente");
        jcbFiltrosAlquiler.addItem("Película");
        jcbFiltrosAlquiler.addItem("Días");
        jcbFiltrosAlquiler.addItem("Promoción");
        jcbFiltrosAlquiler.setCursor(CURSOR_PULSAR);
        busqueda.add(jcbFiltrosAlquiler);
        pNorte.add(busqueda, BorderLayout.NORTH);
        
        TableModel tmAlquiler = new TableModelNoEditable(new String[CANT_FILAS]
                [COLUMNAS_TABLA_ALQUILER.length],COLUMNAS_TABLA_ALQUILER);
        jtAlquiler = new JTable(tmAlquiler);
        jtAlquiler.setCursor(CURSOR_SELECCIONAR);
        jtAlquiler.addMouseListener(tableListener);
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
        bCrearAlquiler.setToolTipText("Inserta un alquiler en la base de "
                                                                 + "datos");
        bCrearAlquiler.addActionListener(botonListener);
        pInfo.add(bCrearAlquiler);
        jcbClienteAlquiler = new JComboBox<>();
        jcbClienteAlquiler.addItem("--Seleccionar cliente--");
        jcbClienteAlquiler.setCursor(CURSOR_PULSAR);
        pInfo.add(jcbClienteAlquiler);
        jcbPeliculaAlquiler = new JComboBox<>();
        jcbPeliculaAlquiler.addItem("--Seleccionar película--");
        jcbPeliculaAlquiler.setCursor(CURSOR_PULSAR);
        pInfo.add(jcbPeliculaAlquiler);
        jcbPromocionAlquiler = new JComboBox<>();
        jcbPromocionAlquiler.addItem("--Seleccionar promoción--");
        jcbPromocionAlquiler.setCursor(CURSOR_PULSAR);
        jcbPromocionAlquiler.setToolTipText("Deseleccionar para no aplicar" + 
                                            "promociones");
        pInfo.add(jcbPromocionAlquiler);
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
        bBorrarAlquiler.addActionListener(botonListener);
        pBorrarModificar.add(bBorrarAlquiler);
        pBorrarModificar.add(new JLabel("Seleccione una ó varias filas para " +
                                        "borrar."));
        bModificarAlquiler = new JButton("Modificar");
        bModificarAlquiler.setAlignmentX(CENTER_ALIGNMENT);
        bModificarAlquiler.setCursor(CURSOR_PULSAR);
        bModificarAlquiler.setToolTipText("Cambia las características del \n" +
                "cliete seleccionado");
        bModificarAlquiler.addActionListener(botonListener);
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
        tfBusquedaCliente.addKeyListener(textfieldListener);
        busqueda.add(tfBusquedaCliente);
        busqueda.add(new JLabel("Filtro:"));
        
        jcbFiltrosCliente = new JComboBox<>();
        jcbFiltrosCliente.addItem("--Todos--");
        jcbFiltrosCliente.addItem("Cliente");
        jcbFiltrosCliente.addItem("Película");
        jcbFiltrosCliente.addItem("Promoción");
        jcbFiltrosCliente.setCursor(CURSOR_PULSAR);
        busqueda.add(jcbFiltrosCliente);
        pNorte.add(busqueda, BorderLayout.NORTH);
        
        TableModel tmCliente = new TableModelNoEditable
        (new String[CANT_FILAS][COLUMNAS_TABLA_CLIENTE.length]
                               ,COLUMNAS_TABLA_CLIENTE);
        jtCliente = new JTable(tmCliente);
        jtCliente.setCursor(CURSOR_SELECCIONAR);
        jtCliente.addMouseListener(tableListener);
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
        bCrearCliente.addActionListener(botonListener);
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
        bBorrarCliente.addActionListener(botonListener);
        pModificar.add(bBorrarCliente);
        bModificarCliente = new JButton("Modificar");
        bModificarCliente.setAlignmentX(CENTER_ALIGNMENT);
        bModificarCliente.setToolTipText("Modifica la fila seleccionada en \n"
                                         + "la base de datos con los datos " +
                                           "ingresados");
        bModificarCliente.setCursor(CURSOR_PULSAR);
        bModificarCliente.addActionListener(botonListener);
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
        tfBusquedaPelicula.addKeyListener(textfieldListener);
        pBusqueda.add(tfBusquedaPelicula);
        pBusqueda.add(new JLabel("Filtros:"));
        jcbFiltrosPelicula = new JComboBox<>();
        jcbFiltrosPelicula.addItem("--Todos--");
        jcbFiltrosPelicula.addItem("Titulo");
        jcbFiltrosPelicula.addItem("Genero");
        jcbFiltrosPelicula.addItem("Cliente");
        jcbFiltrosPelicula.setCursor(CURSOR_PULSAR);
        pBusqueda.add(jcbFiltrosPelicula);
        pCentro.add(pBusqueda);
        
        TableModel modeloPelicula = new TableModelNoEditable(
        new String[30][COLUMNAS_TABLA_PELICULA.length], COLUMNAS_TABLA_PELICULA
        );
        jtPelicula = new JTable(modeloPelicula);
        jtPelicula.setCursor(CURSOR_SELECCIONAR);
        jtPelicula.addMouseListener(tableListener);
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
        bCrearPelicula.setToolTipText("Inserta una palícula en la base de "
                                                                    + "datos");
        bCrearPelicula.addActionListener(botonListener);
        pCrear.add(bCrearPelicula);
        tfTituloPelicula = new JTextField(25);
        tfTituloPelicula.setToolTipText("Título de la película");
        pCrear.add(tfTituloPelicula);
        jcbGeneroPelicula = new JComboBox<>();
        jcbGeneroPelicula.addItem("--Seleccione género--");
        jcbGeneroPelicula.setCursor(CURSOR_PULSAR);
        pCrear.add(jcbGeneroPelicula);
        tfCopiasPelicula = new JTextField(CARACTERES_TEXTFIELD);
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
        bBorrarPelicula.addActionListener(botonListener);
        pGestionar.add(bBorrarPelicula);
        bModificarPelicula = new JButton("Modificar");
        bModificarPelicula.setAlignmentX(CENTER_ALIGNMENT);
        bModificarPelicula.setCursor(CURSOR_PULSAR);
        bModificarAlquiler.setToolTipText("Modifica la película seleccionada");
        bModificarPelicula.addActionListener(botonListener);
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
        tfBusquedaPromocion.addKeyListener(textfieldListener);
        pBusqueda.add(tfBusquedaPromocion);
        pBusqueda.add(new JLabel("Filtros:"));
        jcbFiltrosPromocion = new JComboBox<>();
        jcbFiltrosPromocion.addItem("--Todos--");
        jcbFiltrosPromocion.addItem("Descripción");
        jcbFiltrosPromocion.addItem("Descuento");
        jcbFiltrosPromocion.addItem("Cliente");
        jcbFiltrosPromocion.setCursor(CURSOR_PULSAR);
        pBusqueda.add(jcbFiltrosPromocion);
        pCentro.add(pBusqueda);
        
        TableModel modeloPromo = new TableModelNoEditable
                            (new String[30][COLUMNAS_TABLA_PROMOCION.length], 
                            COLUMNAS_TABLA_PROMOCION);
        jtPromocion = new JTable(modeloPromo);
        jtPromocion.setCursor(CURSOR_SELECCIONAR);
        jtPromocion.addMouseListener(tableListener);
        JScrollPane scroll = new JScrollPane(jtPromocion);
        pCentro.add(scroll);
        
        // Panel sur
        JPanel pSur = new JPanel(new FlowLayout());
        
        JPanel pBotones = new JPanel();
        pBotones.setLayout(new BoxLayout(pBotones, BoxLayout.Y_AXIS));
        bCrearPromocion= new JButton("Insertar");
        bCrearPromocion.setAlignmentX(CENTER_ALIGNMENT);
        bCrearPromocion.setCursor(CURSOR_PULSAR);
        bCrearPromocion.setToolTipText("Inserta una promoción en la base de"
                                                                  + " datos");
        bCrearPromocion.addActionListener(botonListener);
        pBotones.add(bCrearPromocion);
        bModificarPromocion = new JButton("Modificar");
        bModificarPromocion.setAlignmentX(CENTER_ALIGNMENT);
        bModificarPromocion.setCursor(CURSOR_PULSAR);
        bModificarPromocion.setToolTipText("Modifica la promocón seleccionada");
        bModificarPromocion.addActionListener(botonListener);
        pBotones.add(bModificarPromocion);
        bBorrarPromocion = new JButton("Borrar");
        bBorrarPromocion.setAlignmentX(CENTER_ALIGNMENT);
        bBorrarPromocion.setCursor(CURSOR_PULSAR);
        bBorrarPromocion.setToolTipText("Borra la promoción seleccionada");
        bBorrarPromocion.addActionListener(botonListener);
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
        tfBusquedaGenero.addKeyListener(textfieldListener);
        pBusqueda.add(tfBusquedaGenero);
        pBusqueda.add(new JLabel("Filtros:"));
        jcbFiltrosGenero = new JComboBox<>();
        jcbFiltrosGenero.addItem("--Todos--");
        jcbFiltrosGenero.addItem("Descripción");
        jcbFiltrosGenero.addItem("Película");
        jcbFiltrosGenero.setCursor(CURSOR_PULSAR);
        pBusqueda.add(jcbFiltrosGenero);
        
        // Panel centro
        JPanel pCentro = new JPanel(new FlowLayout());
        
        TableModel modeloGenero = new TableModelNoEditable(COLUMNAS_TABLA_GENERO
                                                           ,CANT_FILAS);
        jtGenero = new JTable(modeloGenero);
        jtGenero.setCursor(CURSOR_SELECCIONAR);
        jtGenero.addMouseListener(tableListener);
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
        bCrearGenero.addActionListener(botonListener);
        pOeste.add(bCrearGenero);
        bModificarGenero = new JButton("Modificar");
        bModificarGenero.setAlignmentX(CENTER_ALIGNMENT);
        bModificarGenero.setCursor(CURSOR_PULSAR);
        bModificarGenero.setToolTipText("Modifica el género seleccionado");
        bModificarGenero.addActionListener(botonListener);
        pOeste.add(bModificarGenero);
        bBorrarGenero = new JButton("Borrar");
        bBorrarGenero.setAlignmentX(CENTER_ALIGNMENT);
        bBorrarGenero.setCursor(CURSOR_PULSAR);
        bBorrarGenero.setToolTipText("Borra el género seleccionado");
        bBorrarGenero.addActionListener(botonListener);
        pOeste.add(bBorrarGenero);
        
        String[] colsPeliculas = {"Películas pertenecientes"};
        TableModel modeloPeliculas = new TableModelNoEditable(colsPeliculas, 
                                                              CANT_FILAS);
        jtPeliculasGenero = new JTable(modeloPeliculas);
        jtPeliculasGenero.setCursor(CURSOR_SELECCIONAR);
        JScrollPane scrollPeliculas = new JScrollPane(jtPeliculasGenero);
        scrollPeliculas.setPreferredSize(DIMENSION_BLOQUE);
        pOeste.add(scrollPeliculas);
        pCentro.add(pOeste);
        
        // Adición y retorno
        pestaniaGenero.add(pBusqueda, BorderLayout.NORTH);
        pestaniaGenero.add(pCentro, BorderLayout.CENTER);
        
        return pestaniaGenero;
        
    } // fin crearPestaniaGenero
    
    // Métodos para el llenado de las tablas
    private void llenarTablaAlquiler(Collection<Alquiler> coll)
                                                    throws SQLException{

        // Vacío la tabla
        for (int i = 0; i < jtAlquiler.getModel().getRowCount(); i++) {
            for (int j = 0; j < jtAlquiler.getModel().getColumnCount(); j++) 
            {
                jtAlquiler.setValueAt("", i, j);
            }
        }
        
        if(coll != null){

            // Inserto los valores de la colección
            int i = 0;

            for(Alquiler a : coll){
                Cliente c = facade.obtenerCliente(a.getPk().getIdCliente());
                jtAlquiler.setValueAt(c.toString(), i, 0);
                Pelicula p = facade.obtenerPelicula(a.getPk().getIdPelicula());
                jtAlquiler.setValueAt(p, i, 1);
                jtAlquiler.setValueAt(a.getPk().getFecha().toString(), i, 2);
                jtAlquiler.setValueAt(a.getDias(), i, 3);
                Promocion pr = facade.obtenerPromocion(a.getIdPromocion());
                jtAlquiler.setValueAt(pr.toString(), i, 4);
                i++;
            }
            
        }

    } // fin llenarTablaAlquiler

    private void llenarTablaCliente(Collection<Cliente> coll) 
                                                    throws SQLException{

        // vacío la tabla
        for (int i = 0; i < jtCliente.getModel().getRowCount(); i++) {
            for (int j = 0; j < jtCliente.getModel().getColumnCount(); j++) 
            {
                jtCliente.setValueAt("", i, j);
            }
        }
        
        if(coll != null){

            int i = 0;

            for(Cliente c : coll){
                jtCliente.setValueAt(c.toString(), i, 0);
                jtCliente.setValueAt(c.getPeliculas().size(), i, 1);
                jtCliente.setValueAt(c.getPromociones().size(), i, 2);
                i++;
            }
            
        }

    } // fin llenarTablaCliente

    private void llenarTablaPelicula(Collection<Pelicula> coll) 
                                                    throws SQLException{

        for (int i = 0; i < jtPelicula.getModel().getRowCount(); i++) {
            for (int j = 0; j < jtPelicula.getModel().getColumnCount(); j++) 
            {
                jtPelicula.setValueAt("", i, j);
            }
        }
        
        if(coll != null){

            int i = 0;

            for(Pelicula p: coll){
                jtPelicula.setValueAt(p.toString(), i, 0);
                jtPelicula.setValueAt(p.getGenero().toString(), i, 1);
                jtPelicula.setValueAt(p.getCopias(), i, 2);
                Collection<Cliente> cli = facade.obtenerClientes(p);
                if(cli != null)
                    jtPelicula.setValueAt(cli.size(), i, 3);
                else
                    jtPelicula.setValueAt(0, i, 3);
                i++;
            }
            
        }

    } // fin llenarTablaPelicula

    private void llenarTablaPromocion(Collection<Promocion> coll) 
                                                    throws SQLException{

        // vacío la tabla
        for (int i = 0; i < jtPromocion.getModel().getRowCount(); i++) {
            for (int j = 0;j < jtPromocion.getModel().getColumnCount(); j++) 
            {
                jtPromocion.setValueAt("", i, j);
            }
        }
        
        if(coll != null){

            int i = 0;

            for(Promocion p : coll){
                jtPromocion.setValueAt(p.toString(), i, 0);
                jtPromocion.setValueAt(p.getDescuento(), i, 1);
                jtPromocion.setValueAt(p.getClientes().size(), i, 2);
                i++;
            }
            
        }

    } // fin llenarTablaPromocion

    private void llenarTablaGenero(Collection<Genero> coll) 
                                                        throws SQLException{

        // vacío la tabla
        for (int i = 0; i < jtGenero.getModel().getRowCount(); i++) {
            for (int j = 0;j < jtGenero.getModel().getColumnCount(); j++) 
            {
                jtGenero.setValueAt("", i, j);
            }
        }
        
        if(coll != null){

            int i = 0;

            for(Genero g : coll){
                jtGenero.setValueAt(g.toString(), i, 0);
                jtGenero.setValueAt(g.getPeliculas().size(), i, 1);
                i++;
            }
            
        }

    } // fin llenarTablaGenero
    
    /**
     * LLena los combobox de 
     * @param cod Código del combobox a actualizar
     * 0 : Clientes en la pestaña de alquiler
     * 1 : Películas en la pestaña de alquiler
     * 2 : Promociones en la pestaña de alquiler
     * 3 : Géneros en la pestaña película
     */
    private void llenarComboBox(int cod) throws SQLException{
        
        switch(cod){
            case 0:
                jcbClienteAlquiler.removeAllItems();
                jcbClienteAlquiler.addItem("--Seleccionar cliente");
                Collection<Cliente> collC = facade.obtenerCliente();
                if(collC != null) for(Cliente c : collC){
                    jcbClienteAlquiler.addItem(c.toString());
                }
                break;
            case 1:
                jcbPeliculaAlquiler.removeAllItems();
                jcbPeliculaAlquiler.addItem("--Seleccionar película");
                Collection<Pelicula> collPe = facade.obtenerPelicula();
                if(collPe != null) for(Pelicula p : collPe){
                    jcbPeliculaAlquiler.addItem(p.toString());
                }
                break;
            case 2:
                jcbPromocionAlquiler.removeAllItems();
                jcbPromocionAlquiler.addItem("--Seleccionar promoción");
                Collection<Promocion> collPr = facade.obtenerPromocion();
                if(collPr != null) for(Promocion p : collPr){
                    jcbPromocionAlquiler.addItem(p.toString());
                }
                break;
            case 3:
                jcbGeneroPelicula.removeAllItems();
                jcbGeneroPelicula.addItem("--Seleccione género");
                Collection<Genero> collG = facade.obtenerGenero();
                if(collG != null) for(Genero g : facade.obtenerGenero()){
                    jcbGeneroPelicula.addItem(g.toString());
                }
                break;
        }
        
    } // fin llenarComboBoxes
    
    // Parsea el id de las cadenas obtenidas de los comboBox
    private int idParser(String s){
        StringTokenizer st = new StringTokenizer(s, "-");
        return Integer.parseInt(st.nextToken());
    }
    
    // Clases internas para implementar listeners
    
    class BotonListener implements ActionListener{
        
        // Listener para 
        @Override
        public void actionPerformed(ActionEvent e) {
        
            try { // Aviso de excepciones a través de ventanas de advertencia
                
                // pestaña de alquileres
                if(e.getSource() == bCrearAlquiler) {
                    // Arroja una excepción en caso de que se hayan elegido
                    // casillas no válidas
                    if(jcbClienteAlquiler.getSelectedIndex() == 0 ||
                       jcbPeliculaAlquiler.getSelectedIndex() == 0){
                        throw new NullPointerException("Falta seleccionar "
                                                        + "parámetros");
                    }
                    
                    // variables para la creación de la nueva fila
                    int idCliente = idParser((String)jcbClienteAlquiler
                                                            .getSelectedItem());
                    int idPelicula = idParser((String)jcbPeliculaAlquiler
                                                            .getSelectedItem());
                    int idPromocion = 0, dias;
                    Date fecha = new Date(System.currentTimeMillis());
                    
                    AlquilerPK pk = new AlquilerPK(idPelicula,idCliente,fecha);
                    
                    if(jcbPromocionAlquiler.getSelectedIndex() != 0)
                        idPromocion = idParser((String)jcbPromocionAlquiler
                                                            .getSelectedItem());
                    
                    dias = Integer.parseInt((String)tfDiasAlquiler.getText());
                    // Actualización de la base de datos
                    Alquiler alquiler = new Alquiler(pk, dias, idPromocion);
                    facade.crearOActualizarAlquiler(alquiler);
                    // Actualización de recursos
                    tfDiasAlquiler.setText("");
                    jcbClienteAlquiler.setSelectedIndex(0);
                    jcbPeliculaAlquiler.setSelectedIndex(0);
                    jcbPromocionAlquiler.setSelectedIndex(0);
                    jcbFiltrosAlquiler.setSelectedIndex(0);
                    llenarTablaAlquiler(facade.obtenerAlquiler());
                    
                }// fin botón Insertar Alquiler

                if(e.getSource() == bModificarAlquiler){
                    
                    int row = jtAlquiler.getSelectedRow();
                    // analiza que todas las condiciones se cumplan para poder
                    // realizar una modificación
                    if(row == -1 || jcbClienteAlquiler.getSelectedIndex() == 0
                       || jcbPeliculaAlquiler.getSelectedIndex() == 0 ||
                       tfDiasAlquiler.getText().equals("")){
                        
                        throw new NullPointerException("Fla no seleccionada"
                                + " ó datos inválidos");
                        
                    }
                        
                    // Obtengo la llave primaria
                    AlquilerPK pk = new AlquilerPK();
                    
                    String s = (String)jtAlquiler.getValueAt(row, 0);
                    pk.setIdCliente(idParser(s));
                    s = (String)jtAlquiler.getValueAt(row, 1);
                    pk.setIdPelicula(idParser(s));
                    s = (String)jtAlquiler.getValueAt(row, 2);
                    StringTokenizer st = new StringTokenizer(s, "/");
                    int dia = Integer.parseInt(st.nextToken());
                    int mes = Integer.parseInt(st.nextToken());
                    int anio = Integer.parseInt(st.nextToken());
                    Date fecha = new Date(anio, mes, dia);
                    pk.setFecha(fecha);
                    
                    Alquiler a = facade.obtenerAlquiler(pk);
                    facade.borrar(a); // se deshace de la anterior
                    // Relizo las modificaciones
                    pk = new AlquilerPK();
                    pk.setIdCliente(idParser((String)jcbClienteAlquiler
                                                           .getSelectedItem()));
                    pk.setIdPelicula(idParser((String)jcbPeliculaAlquiler
                                                           .getSelectedItem()));
                    pk.setFecha(fecha);
                    dia = Integer.parseInt(tfDiasAlquiler.getText());
                    int idPromocion = idParser((String)jcbPromocionAlquiler
                                                            .getSelectedItem());
                    a = new Alquiler(pk, dia, idPromocion);
                    
                    facade.crearOActualizarAlquiler(a);
                    // Actualización de recursos visuales
                    tfDiasAlquiler.setText("");
                    jcbClienteAlquiler.setSelectedIndex(0);
                    jcbPeliculaAlquiler.setSelectedIndex(0);
                    jcbPromocionAlquiler.setSelectedIndex(0);
                    jcbFiltrosAlquiler.setSelectedIndex(0);
                    llenarTablaAlquiler(facade.obtenerAlquiler());
                    
                } // fin botón Modificar Alquiler
                
                if(e.getSource() == bBorrarAlquiler){

                    if(jtAlquiler.getSelectedRowCount() != 0){
                        // Recorre las filas de la tabla y va borrandolas de la
                        // base de datos
                        AlquilerPK pk;
                        Alquiler a;
                        int[] filas = jtAlquiler.getSelectedRows();

                        for (int i = 0; i < filas.length; i++) {
                            pk = new AlquilerPK();
                            // Se obtiene una llave primaria compuesta de la 
                            // tabla
                            String s = (String)jtAlquiler
                                                    .getValueAt(filas[i], 0);
                            pk.setIdCliente(idParser(s));
                            s = (String)jtAlquiler.getValueAt(filas[i], 1);
                            pk.setIdPelicula(idParser(s));
                            s = (String)jtAlquiler.getValueAt(filas[i], 2);
                            StringTokenizer st = new StringTokenizer(s, "/");
                            int dia = Integer.parseInt(st.nextToken());
                            int mes = Integer.parseInt(st.nextToken());
                            int anio = Integer.parseInt(st.nextToken());
                            pk.setFecha(new Date(anio, mes, dia));

                            a = facade.obtenerAlquiler(pk);

                            facade.borrar(a);
                        }
                        
                        jcbFiltrosAlquiler.setSelectedIndex(0);
                        llenarTablaAlquiler(facade.obtenerAlquiler());
                        
                    } else throw new NullPointerException("Alquiler no " + 
                                                          "seleccionado");


                } // fin botón Borrar Alquiler
            
                // Pestaña de clientes
            
                if(e.getSource() == bCrearCliente){

                    Cliente c = new Cliente();
                    String nombreCliente = InputVerifier
                                    .verificarTexto(tfNombreCliente.getText());
                    c.setNombre(nombreCliente);
                    facade.crearOActualizarCliente(c);

                    // Actualización de recursos visuales
                    tfNombreCliente.setText("");
                    llenarComboBox(0);
                    jcbFiltrosCliente.setSelectedIndex(0);
                    llenarTablaCliente(facade.obtenerCliente());
                    
                } // fin botón Crear Cliente

                if(e.getSource() == bModificarCliente){
                    
                    int fila = jtCliente.getSelectedRow();
                    
                    if(fila != -1){
                        
                        int idCliente = idParser((String)jtCliente
                                                        .getValueAt(fila, 0));
                        
                        Cliente c = facade.obtenerCliente(idCliente);
                        c.setNombre(InputVerifier
                                    .verificarTexto(tfNombreCliente.getText()));
                        
                        facade.crearOActualizarCliente(c);
                        
                        tfNombreCliente.setText("");
                        llenarComboBox(0);
                        jcbFiltrosCliente.setSelectedIndex(0);
                        llenarTablaCliente(facade.obtenerCliente());
                        
                    } else {
                        throw new NullPointerException("Cliente no"
                                + " seleccionado");
                    }
                    
                } //fin botón Modficar Cliente
                
                if(e.getSource() == bBorrarCliente){

                    if(jtCliente.getSelectedRowCount() != 0){

                        int[] filas = jtCliente.getSelectedRows();

                        for (int i = 0; i < filas.length; i++) {

                        int id = idParser((String)jtCliente
                                                .getValueAt(filas[i], 0));

                        Cliente c = facade.obtenerCliente(id);

                        facade.borrar(c);
                        }

                        llenarComboBox(0);
                        jcbFiltrosCliente.setSelectedIndex(0);
                        llenarTablaCliente(facade.obtenerCliente());
                        
                    } else {
                        throw new NullPointerException("Cliente no "
                                                       + "seleccionado");
                    }

                } // fin botón Borrar Cliente y botón Modificar Cliente
                
                // Pestaña de películas
            
                if(e.getSource() == bCrearPelicula){

                    if(jcbGeneroPelicula.getSelectedIndex() != 0){

                       Pelicula p = new Pelicula();
                       p.setTitulo(InputVerifier
                               .verificarTexto(tfTituloPelicula.getText()));
                       String sCopias = InputVerifier
                                    .verificarTexto(tfCopiasPelicula.getText());
                       p.setCopias(Integer.parseInt(sCopias));
                       int idGenero = idParser((String)jcbGeneroPelicula
                                                            .getSelectedItem());
                       p.setGenero(facade.obtenerGenero(idGenero));

                       facade.crearOActualizarPelicula(p);
                       
                       tfCopiasPelicula.setText("");
                       tfTituloPelicula.setText("");
                       llenarComboBox(1);
                       jcbGeneroPelicula.setSelectedIndex(0);
                       jcbFiltrosPelicula.setSelectedIndex(0);
                       llenarTablaPelicula(facade.obtenerPelicula());

                   } else throw new NullPointerException("Género no "
                                                       + "seleccionado");

                } // fin botón Crear Películas
                
                if(e.getSource() == bModificarPelicula){
                    
                    int fila = jtPelicula.getSelectedRow();
                    
                    if(fila != -1){
                        
                        // Obtengo valores válidos
                        String titulo = InputVerifier
                                .verificarTexto(tfTituloPelicula.getText());
                        int copias = Integer
                                        .parseInt(tfCopiasPelicula.getText());
                        
                        int idPelicula = idParser((String)jtPelicula
                                                    .getValueAt(fila, 0));
                        
                        Pelicula p = facade.obtenerPelicula(idPelicula);
                        
                        p.setTitulo(titulo);
                        p.setCopias(copias);
                        // Cambia el género
                        Genero g = null;
                        if(jcbGeneroPelicula.getSelectedIndex() !=0){
                            int idGenero = idParser
                                  ((String)jcbGeneroPelicula.getSelectedItem());
                            g = facade.obtenerGenero(idGenero);
                        }
                        p.setGenero(g);
                        facade.crearOActualizarPelicula(p);
                        
                        tfCopiasPelicula.setText("");
                        tfTituloPelicula.setText("");
                        llenarComboBox(1);
                        jcbGeneroPelicula.setSelectedIndex(0);
                        jcbFiltrosPelicula.setSelectedIndex(0);
                        llenarTablaPelicula(facade.obtenerPelicula());
                        
                    } else throw new NullPointerException("Película no "
                            + "seleccionada");
                    
                } // fin botón Modificar Película
                
                if(e.getSource() == bBorrarPelicula){
                    
                    if(jtPelicula.getSelectedRowCount() != 0){
                        
                        int[] filas = jtPelicula.getSelectedRows();
                        
                        for (int i = 0; i < filas.length; i++) {
                            
                            int id = idParser((String)jtPelicula
                                                    .getValueAt(filas[i], 0));
                            Pelicula p = facade.obtenerPelicula(id);
                            facade.borrar(p);
                        }
                        
                        llenarComboBox(1);
                        jcbFiltrosPelicula.setSelectedIndex(0);
                        llenarTablaPelicula(facade.obtenerPelicula());
                        
                    } else
                        throw new NullPointerException("No se han seleccionado"
                                                     + "películas");
                    
                } // fin botón Borrar Película y Modificar Película
                
                // Pestaña de promociones
                
                if(e.getSource() == bCrearPromocion){
                    
                    Promocion p = new Promocion();
                    
                    p.setDescripcion(InputVerifier
                                .verificarTexto(tfDescripcionPromo.getText()));
                    String sDescuento = InputVerifier
                                    .verificarTexto(tfDescuentoPromo.getText());
                    p.setDescuento(Integer.parseInt(sDescuento));
                    
                    facade.crearOActualizarPromocion(p);
                    
                    tfDescuentoPromo.setText("");
                    tfDescripcionPromo.setText("");
                    llenarComboBox(2);
                    jcbFiltrosPelicula.setSelectedIndex(0);
                    llenarTablaPromocion(facade.obtenerPromocion());
                    
                } // fin botón Crear Promoción
                
                if(e.getSource() == bModificarPromocion){
                    
                    int fila = jtPromocion.getSelectedRow();
                    
                    if(fila != -1){
                        
                        int idPromocion = idParser((String)jtPromocion
                                                        .getValueAt(fila, 0));
                        
                        String descripcion = InputVerifier
                                  .verificarTexto(tfDescripcionPromo.getText());
                        int descuento = Integer.parseInt(tfDescuentoPromo
                                               .getText());
                        
                        Promocion p = facade.obtenerPromocion(idPromocion);
                        p.setDescripcion(descripcion);
                        p.setDescuento(descuento);
                        
                        facade.crearOActualizarPromocion(p);
                        llenarComboBox(2);
                        tfDescuentoPromo.setText("");
                        tfDescripcionPromo.setText("");
                        jcbFiltrosPelicula.setSelectedIndex(0);
                        llenarTablaPromocion(facade.obtenerPromocion());
                        
                    } else throw new NullPointerException("No se ha "
                            + "seleccionado un promoción");
                    
                } // fin botón Modificar Promoción
                
                if(e.getSource() == bBorrarPromocion){
                    
                    if(jtPromocion.getSelectedRowCount() != 0){
                        
                        int[] filas = jtPromocion.getSelectedRows();
                        
                        for (int i = 0; i < filas.length; i++) {
                            int id = idParser((String)jtPromocion
                                                    .getValueAt(filas[i], 0));
                            facade.borrar(facade.obtenerPromocion(id));
                        }
                        
                        llenarComboBox(2);
                        jcbFiltrosPelicula.setSelectedIndex(0);
                        llenarTablaPromocion(facade.obtenerPromocion());
                        
                    } else throw new NullPointerException("No se han "
                            + "seleccionado promociones");
                    
                } // fin botón Borrar Promoción y botón Modificar Promoción
                
                // Pestaña genero
                
                if(e.getSource() == bCrearGenero){
                    
                    Genero g = new Genero();
                    g.setDescripcion(InputVerifier
                                .verificarTexto(tfDescripcionGenero.getText()));
                    facade.crearOActualizarGenero(g);
                    
                    tfDescripcionGenero.setText("");
                    llenarComboBox(3);
                    jcbFiltrosGenero.setSelectedIndex(0);
                    llenarTablaGenero(facade.obtenerGenero());
                    
                } // fin botón Crear Género
                
                if(e.getSource() == bModificarGenero){
                    
                    int fila = jtGenero.getSelectedRow();
                    
                    if(fila != -1){
                        
                        int idGenero = idParser((String)jtGenero
                                                        .getValueAt(fila, 0));
                        String descripcion = InputVerifier.verificarTexto
                                                (tfDescripcionGenero.getText());
                        
                        Genero g = facade.obtenerGenero(idGenero);
                        g.setDescripcion(descripcion);
                        
                        facade.crearOActualizarGenero(g);
                        
                        tfDescripcionGenero.setText("");
                        llenarComboBox(3);
                        jcbFiltrosGenero.setSelectedIndex(0);
                        llenarTablaGenero(facade.obtenerGenero());
                        
                    } else throw new NullPointerException("No se ha "
                            + "seleccionado un género");
                    
                } // fin botón Modificar Género
                
                if(e.getSource() == bBorrarGenero){
                    
                    if(jtGenero.getSelectedRowCount() != 0){
                        
                        int[] filas = jtGenero.getSelectedRows();
                        
                        for (int i = 0; i < filas.length; i++) {
                            
                            int id = idParser
                                    ((String)jtGenero.getValueAt(filas[i], 0));
                            facade.borrar(facade.obtenerGenero(id));
                        }
                        
                        llenarComboBox(3);
                        jcbFiltrosGenero.setSelectedIndex(0);
                        llenarTablaGenero(facade.obtenerGenero());
                        
                    } else
                        throw new NullPointerException("No se ha seleccionado"
                                + "un género");
                    
                } // fin botón Borrar Género y Modificar Género
                
            } catch (NullPointerException npe) {
                JOptionPane.showMessageDialog(null, npe.getMessage(), 
                            "Error de nulidad", JOptionPane.ERROR_MESSAGE);
            } catch (SQLException sqle){
                JOptionPane.showMessageDialog(null, sqle.getMessage(), 
                               "Error de datos", JOptionPane.ERROR_MESSAGE);
            } catch (NumberFormatException nfe){
                JOptionPane.showMessageDialog(null, nfe.getMessage(), 
                            "Error de formato", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex){
                JOptionPane.showMessageDialog(null, ex.getMessage(), 
                            "Error desconocido", JOptionPane.ERROR_MESSAGE);
            }
            
        } // fin actionPerformed
        
    } // fin BotonListener
    
    class TextFieldListener implements KeyListener{
        
        @Override
        public void keyTyped(KeyEvent e) {
            
            try {
                
                if(e.getSource() == tfBusquedaAlquiler){
                
                String texto = tfBusquedaAlquiler.getText();
                
                if(!texto.equals(""))
                    texto = InputVerifier.verificarTexto(texto);
                
                // Condiciona la búsqueda según los filtros elegidos
                switch(jcbFiltrosAlquiler.getSelectedIndex()){
                    
                    case 0:
                        llenarTablaAlquiler(facade.obtenerAlquiler());
                        break;
                    case 1:
                        
                        Collection<Cliente> clientes = facade
                                                        .obtenerCliente(texto);
                        Collection<Alquiler> collC = new ArrayList<>();
                        for(Cliente c : clientes){
                            collC.addAll(facade.obtenerAlquiler(c));
                        }
                        llenarTablaAlquiler(collC);
                        
                        break;
                    case 2:
                        
                        Collection<Pelicula> peliculas = facade
                                                        .obtenerPelicula(texto);
                        Collection<Alquiler> collP = new ArrayList<>();
                        
                        for(Pelicula p : peliculas){
                            collP.addAll(facade.obtenerAlquiler(p));
                        }
                        llenarTablaAlquiler(collP);
                        
                        break;
                    case 3:
                        int dias = Integer.parseInt(texto);
                        llenarTablaAlquiler(facade.obtenerAlquiler(dias));
                        break;
                    case 4:
                        
                        Collection<Promocion> promociones = facade
                                                       .obtenerPromocion(texto);
                        Collection<Alquiler> collPro = new ArrayList<>();
                        
                        for(Promocion p : promociones){
                            collPro.addAll(facade.obtenerAlquiler(p));
                        }
                        llenarTablaAlquiler(collPro);
                        
                        break;
                    }
                } // fin TextField Alquiler
                
                if(e.getSource() == tfBusquedaCliente){
                    
                    String texto = tfBusquedaCliente.getText();
                    
                    if(!texto.equals(""))
                        texto = InputVerifier.verificarTexto(texto);
                    
                    switch(jcbFiltrosCliente.getSelectedIndex()){
                        case 0:
                            llenarTablaCliente(facade.obtenerCliente());
                            break;
                        case 1:
                            llenarTablaCliente(facade.obtenerCliente(texto));
                            break;
                        case 2:
                            Collection<Cliente> collC = new ArrayList<>();
                            
                            for(Pelicula p : facade.obtenerPelicula(texto)){
                                collC.addAll(p.getClientes());
                            }
                            llenarTablaCliente(collC);
                            break;
                        case 3:
                            Collection<Cliente> collP = new ArrayList<>();
                            
                            for(Promocion p : facade.obtenerPromocion(texto)){
                                collP.addAll(p.getClientes());
                            }
                            llenarTablaCliente(collP);
                            break;
                    }
                    
                } // fin Textfield Clientes
                
                if(e.getSource() == tfBusquedaPelicula){
                    
                    String texto = tfBusquedaPelicula.getText();
                    
                    if(!texto.equals(""))
                        texto = InputVerifier.verificarTexto(texto);
                    
                    if(texto.equals("")){
                        llenarTablaPelicula(facade.obtenerPelicula());
                        return;
                    }
                    
                    switch(jcbFiltrosPelicula.getSelectedIndex()){
                        case 0:
                            llenarTablaPelicula(facade.obtenerPelicula());
                            break;
                        case 1:
                            llenarTablaPelicula(facade.obtenerPelicula(texto));
                            break;
                        case 2:
                            Collection<Pelicula> collG = new ArrayList<>();
                            for(Genero g : facade.obtenerGenero(texto)){
                                collG.addAll(g.getPeliculas());
                            }
                            llenarTablaPelicula(collG);
                            break;
                        case 3:
                            Collection<Pelicula> collC = new ArrayList<>();
                            for(Cliente c : facade.obtenerCliente(texto)){
                                collC.addAll(c.getPeliculas());
                            }
                            llenarTablaPelicula(collC);
                            break;
                    }
                    
                } // fin Textfield Película
                
                if(e.getSource() == tfBusquedaPromocion){
                    
                    String texto = tfBusquedaPromocion.getText();
                    
                    if(!texto.equals(""))
                        texto = InputVerifier.verificarTexto(texto);
                    
                    if(texto.equals("")){
                        llenarTablaPromocion(facade.obtenerPromocion());
                        return;
                    }
                    
                    switch(jcbFiltrosPromocion.getSelectedIndex()){
                        
                        case 0:
                            llenarTablaPromocion(facade.obtenerPromocion());
                            break;
                        case 1:
                            llenarTablaPromocion(facade
                                                    .obtenerPromocion(texto));
                            break;
                        case 2:
                            int monto = Integer.parseInt(texto);
                            llenarTablaPromocion(facade
                                               .obtenerPromocion(monto, monto));
                            break;
                        case 3:
                            Collection<Promocion> coll = new ArrayList<>();
                            
                            for(Cliente c : facade.obtenerCliente(texto)){
                                coll.addAll(c.getPromociones());
                            }
                            llenarTablaPromocion(coll);
                            break;
                        
                    }
                    
                } // fin Textfield Promoción
                
                if(e.getSource() == tfBusquedaGenero){
                    
                    String texto = tfBusquedaGenero.getText();
                    
                    if(!texto.equals(""))
                        texto = InputVerifier.verificarTexto(texto);
                    
                    if(texto.equals("")){
                        llenarTablaGenero(facade.obtenerGenero());
                        return;
                    }
                    
                    switch(jcbFiltrosGenero.getSelectedIndex()){
                        case 0:
                            llenarTablaGenero(facade.obtenerGenero());
                            break;
                        case 1:
                            llenarTablaGenero(facade.obtenerGenero(texto));
                            break;
                        case 2:
                            Collection<Genero> coll = new ArrayList<>();
                            for(Pelicula p : facade.obtenerPelicula(texto)){
                                coll.add(p.getGenero());
                            }
                            llenarTablaGenero(coll);
                            break;
                    }
                    
                } // fin Textfield Género
                
            } catch (SQLException sqle) {
                JOptionPane.showMessageDialog(null, sqle.getMessage(), 
                        "Error de datos", JOptionPane.ERROR_MESSAGE);
            } catch (NullPointerException npe) {
                /* se ignora el texto vacío ya que una cadena vaciá retorna
                 * todos los elementos de una fila cuando se realizan búsquedas
                 * por nombre en la base de datos.
                 */
            } catch (NumberFormatException nfe) {
                JOptionPane.showMessageDialog(null, nfe.getMessage(), 
                        "Error de formato", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), 
                        "Error desconocido", JOptionPane.ERROR_MESSAGE);
            }
            
        } // fin keyTyped

        @Override
        public void keyPressed(KeyEvent e) {}

        @Override
        public void keyReleased(KeyEvent e) {}
        
    } // fin TextFieldListener
    
    class TableListener implements MouseListener{

        @Override
        public void mouseClicked(MouseEvent e) {
        
            try {
                
                if(e.getSource() == jtCliente){
                    
                    String fila = (String)jtCliente
                                    .getValueAt(jtCliente.getSelectedRow(), 0);
                    
                    // Contempla is la fila seleccionada está vacía
                    if(fila.equals("")) return;
                    
                    // Obtengo el id de la fila seleccionada
                    int id = idParser(fila);
                    Cliente c = facade.obtenerCliente(id);
                    Collection<Pelicula> collPe = facade.obtenerPeliculas(c);
                    Collection<Promocion> collPr = facade.obtenerPromociones(c);
                    
                    // vacio las tablas auxiliares
                    
                    for (int i = 0; i < jtPeliculasCliente.getRowCount(); i++) {
                        jtPeliculasCliente.setValueAt("", i, 0);
                        jtPromocionesCliente.setValueAt("", i, 0);
                    }
                    
                    // lleno las tablas
                    int i = 0;
                    
                    if(collPe != null) for(Pelicula p : collPe){
                        jtPeliculasCliente.setValueAt(p.toString(), i, 0);
                        i++;
                    }
                    i = 0;
                    if(collPr != null) for(Promocion p : collPr){
                        jtPromocionesCliente.setValueAt(p.toString(), i, 0);
                        i++;
                    }
                    
                } // fin Tabla Cliente
                
                if(e.getSource() == jtPelicula){
                    
                    String fila = (String)jtPelicula
                                   .getValueAt(jtPelicula.getSelectedRow(), 0);
                    
                    // Contempla is la fila seleccionada está vacía
                    if(fila.equals("")) return;
                    
                    // Llena las tablas auxiliares
                    int id = idParser(fila);
                    Collection<Cliente> coll = facade
                                   .obtenerClientes(facade.obtenerPelicula(id));
                    
                    for (int i = 0; i < jtClientesPelicula.getRowCount(); i++) {
                        jtClientesPelicula.setValueAt("", i, 0);
                    }
                    
                    if(coll != null){
                        int i = 0;
                        for(Cliente c : coll){
                            jtClientesPelicula.setValueAt(c.toString(), i, 0);
                            i++;
                        }
                    }
                    
                } // fin Tabla Películas
                
                if(e.getSource() == jtPromocion){
                    
                    String fila = (String)jtPromocion
                                  .getValueAt(jtPromocion.getSelectedRow(), 0);
                    
                    // Contempla is la fila seleccionada está vacía
                    if(fila.equals("")) return;
                    
                    // Llena las tablas auxiliares
                    int id = idParser(fila);
                    
                    Collection<Cliente> coll = facade
                                  .obtenerClientes(facade.obtenerPromocion(id));
                    
                    for (int i = 0; i < jtClientesPromocion.getRowCount(); i++){
                        jtClientesPromocion.setValueAt("", i, 0);
                    }
                    
                    if(coll != null){
                        int i = 0;
                        for(Cliente c : coll){
                            jtClientesPromocion.setValueAt(c.toString(), i, 0);
                            i++;
                        }
                    }
                    
                } // fin Tabla Promoción
                
                if(e.getSource() == jtGenero){
                    
                    String fila = (String)jtGenero
                                     .getValueAt(jtGenero.getSelectedRow(), 0);
                    
                    // Contempla is la fila seleccionada está vacía
                    if(fila.equals("")) return;
                    
                    // Llena las tablas auxiliares
                    int id = idParser(fila);
                    Collection<Pelicula> coll = facade
                                    .obtenerPelicula(facade.obtenerGenero(id));
                    
                    for (int i = 0; i < jtPeliculasGenero.getRowCount(); i++) {
                        jtPeliculasGenero.setValueAt("", i, 0);
                    }
                    
                    if(coll != null){
                        int i = 0;
                        for(Pelicula p : coll){
                            jtPeliculasGenero.setValueAt(p.toString(), i, 0);
                            i++;
                        }
                    }
                    
                } // fin Tabla Géneros
                
            } catch (SQLException sqle) {
                JOptionPane.showMessageDialog(null, sqle.getMessage(), 
                        "Error de datos", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), 
                        "Error desconocido", JOptionPane.ERROR_MESSAGE);
            }
        
        } // fin mouseClicked

        @Override
        public void mousePressed(MouseEvent e) {}

        @Override
        public void mouseReleased(MouseEvent e) {}

        @Override
        public void mouseEntered(MouseEvent e) {}

        @Override
        public void mouseExited(MouseEvent e) {}
        
    } // fin TableListener
    
}