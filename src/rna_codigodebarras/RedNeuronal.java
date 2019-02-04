package rna_codigodebarras;

import java.awt.Image;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import org.encog.ml.data.basic.BasicMLData;
import org.encog.platformspecific.j2se.data.image.ImageMLData;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import org.encog.ml.data.basic.BasicMLData;
import org.encog.ml.train.strategy.ResetStrategy;
import org.encog.neural.networks.BasicNetwork;
import org.encog.neural.networks.training.propagation.back.Backpropagation;
import org.encog.platformspecific.j2se.TrainingDialog;
import org.encog.platformspecific.j2se.data.image.ImageMLData;
import org.encog.platformspecific.j2se.data.image.ImageMLDataSet;
import org.encog.util.downsample.Downsample;
import org.encog.util.downsample.RGBDownsample;
import org.encog.util.simple.EncogUtility;




public class RedNeuronal extends javax.swing.JFrame {
    
      private ArrayList<String> imagenes; //Lista que contiene la direccion relativa de las imagenes a reconocer mediante la RNA
        private ArrayList<String> palabra;
        String palabraTotal="";
        private Downsample downsample; //Se encargarÃ¡ de transformar la imagen a una entrada para la red 
        private final int ImgWidth = 64; //Ancho de la imagen
        private final int ImgHeight = 64; //Alto de la imagen 
        private String cadenaImagen;
        
        private BasicNetwork network; // La red neuronal
        private final ImageMLDataSet training; // Clase se encargarÃ¡ del entrenamiento de la red
        private final int capasOcultas = 0; //Numero de capas ocultas para la red // Clase se encargarÃ¡ del entrenamiento de la red
        
        private ArrayList<Imagen> CatalogoImagenes;// Se almacenan las imagenes que se usaran en la red 
        private ArrayList<String> TotalCodigosDeBarra; //lista de codigos  guardados en la lista
        private ArrayList<String> Datos;  
    public RedNeuronal() 
    {
        initComponents();
        setLocationRelativeTo (null);
        setResizable(false);
        setTitle("Agente Codigo de Barras");
        
        imagenes = new ArrayList<>();
        CatalogoImagenes = new ArrayList<>();
        downsample = new RGBDownsample();
        training = new ImageMLDataSet(downsample, false, 1, 0);
        palabra=new ArrayList<>();                                
        this.lblMensaje.setText("");
        Datos=new ArrayList<>();
        this.lblProducto.setVisible(false);
        
        TotalCodigosDeBarra=new ArrayList<>();
        Datos=new ArrayList<>();
        this.lblCodigoDeBarra.setVisible(false);

    }
    
    

    
  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFileChooser2 = new javax.swing.JFileChooser();
        elegirfichero = new javax.swing.JFileChooser();
        btnCargarImagenes = new javax.swing.JButton();
        btnEntrenar = new javax.swing.JButton();
        lblMensaje = new javax.swing.JLabel();
        lblCodigoDeBarra = new javax.swing.JLabel();
        btnVerificarPalabra = new javax.swing.JButton();
        btnAbrirImagenCodigoDeBarra = new javax.swing.JButton();
        etiImagen = new javax.swing.JLabel();
        lblMessage = new javax.swing.JLabel();
        lblProducto = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        lbxCodigos = new javax.swing.JList();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnCargarImagenes.setText("Cargar Imagenes de codigos de barra");
        btnCargarImagenes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCargarImagenesActionPerformed(evt);
            }
        });

        btnEntrenar.setText("Entrenar");
        btnEntrenar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEntrenarActionPerformed(evt);
            }
        });

        btnVerificarPalabra.setText("Verificar codigo de barra");
        btnVerificarPalabra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerificarPalabraActionPerformed(evt);
            }
        });

        btnAbrirImagenCodigoDeBarra.setText("Abrir Imagen de Codigo de Barra");
        btnAbrirImagenCodigoDeBarra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAbrirImagenCodigoDeBarraActionPerformed(evt);
            }
        });

        etiImagen.setName(""); // NOI18N

        lblProducto.setText("jLabel1");

        jScrollPane1.setViewportView(lbxCodigos);

        jLabel1.setText("Lista de Codigos de barra ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblMensaje, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(etiImagen, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblCodigoDeBarra, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 78, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(btnAbrirImagenCodigoDeBarra, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnEntrenar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnCargarImagenes, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 232, Short.MAX_VALUE)
                                .addComponent(btnVerificarPalabra, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jLabel1))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(lblCodigoDeBarra)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(etiImagen, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblMensaje)
                        .addGap(109, 109, 109))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnAbrirImagenCodigoDeBarra)
                        .addGap(16, 16, 16)
                        .addComponent(btnCargarImagenes)
                        .addGap(18, 18, 18)
                        .addComponent(btnEntrenar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnVerificarPalabra)
                        .addGap(33, 33, 33)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEntrenarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEntrenarActionPerformed
        
        try {
             //Se crea la red neuronal
             training.downsample(ImgHeight, ImgWidth);        
             network = EncogUtility.simpleFeedForward(training.getInputSize(), capasOcultas, 0, training.getIdealSize(), false);
             System.out.println("Red Neural creada!");     
             this.EntrenarRed(); // TODO add your handling code here:
             this.btnEntrenar.setEnabled(false);
            } catch (Exception ex) {
              JOptionPane.showMessageDialog(null,"Debe cargar las imagenes antes Entrenar!!!");
            }
    }//GEN-LAST:event_btnEntrenarActionPerformed

    private void btnCargarImagenesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCargarImagenesActionPerformed
        // TODO add your handling code here:
        //Se insertan las direcciones de las imagenes
       imagenes.add("abc//0 79400 30116 1.png"); 
       
       imagenes.add("abc//1 27035 24076 0.png");
       imagenes.add("abc//0 012656 100485.png");
       imagenes.add("abc//0 28995 33312 5.png"); 
       imagenes.add("abc//0 51141 23326 0.png");
       imagenes.add("abc//0 70640 01158 2.png");
       imagenes.add("abc//0 88169 00651 4.png");
       imagenes.add("abc//0 99176 23269 8.png");
       //imagenes.add("abc//0 418000 501502.png"); 
//       imagenes.add("abc//0 790920 060450.png");
//       
//       imagenes.add("abc//1 234567 890128.png"); imagenes.add("abc//3 613865 284316.png");
//       imagenes.add("abc//4 055339 942442.png"); imagenes.add("abc//5 449000 000996.png");
//       imagenes.add("abc//6 77916 57611 2.png"); imagenes.add("abc//6 87766 30009 6.jpg");
//       imagenes.add("abc//6 910281 163360.jpg"); imagenes.add("abc//7 02240 42501 7.png");
//       imagenes.add("abc//7 13970 00001 6.jpg"); imagenes.add("abc//7 34646 13251 0.jpg");
//       imagenes.add("abc//7 42100 16002 3.jpg"); imagenes.add("abc//7 50894 62144 6.jpg");
//       imagenes.add("abc//7 50894 64104 8.jpg"); imagenes.add("abc//7 59735 00017 4.jpg");
//       imagenes.add("abc//7 290002 582854.jpg"); imagenes.add("abc//7 413100 084376.jpg");
//       imagenes.add("abc//7 411000 208311.jpg"); imagenes.add("abc//7 420002 000106.jpg");
//       imagenes.add("abc//7 421000 843344.jpg"); imagenes.add("abc//7 421000 846956.jpg");
//       imagenes.add("abc//7 421001 492732.jpg"); imagenes.add("abc//7 441001 698644.jpg");
//       imagenes.add("abc//7 421343 200019.jpg"); imagenes.add("abc//7 441012 220117.jpg");
//       imagenes.add("abc//7 426500 210757.jpg"); 
//       
//       imagenes.add("abc//7 441017 910082.jpg"); imagenes.add("abc//7 613032 258160.jpg");
//       imagenes.add("abc//7 441057 213501.jpg"); imagenes.add("abc//7 613035 265097.jpg");
//       imagenes.add("abc//7 441057 285065.jpg"); imagenes.add("abc//7 702195 000041.jpg");
//       imagenes.add("abc//7 441105 624402.jpg"); imagenes.add("abc//7 751851 007894.jpg");
//       imagenes.add("abc//7 501058 617477.jpg"); imagenes.add("abc//7 802800 709670.jpg");
//       imagenes.add("abc//7 501059 292543.jpg"); imagenes.add("abc//8 48958 02040 0.jpg");
//       imagenes.add("abc//7 501234 567893.png"); imagenes.add("abc//8 48958 02040 0.png");
//       imagenes.add("abc//8 470007 568339.jpg"); 
//       
        //aÃ±adir todas las imagenes al catalogo que se usarÃ¡ en la red
        for(String imagen:imagenes){ //Por cada imagen en la lista de direcciones de imagenes
            CatalogoImagenes.add(new Imagen(imagen));  //aÃ±adir una nueva clase de tipo imagen al catalogo
            System.out.println("Imagen '" + imagen + "' aÃ±adida al catalogo!");
        }
        
           for(Imagen imagen : CatalogoImagenes){ // por cada imagen en el catalogo
            BasicMLData salida = new BasicMLData(CatalogoImagenes.size()); // se crean las salidas de la red de acuerdo al numero de imagenes en el catalogo
            ImageMLData img = null; 
            try {
                img = new ImageMLData(ImageIO.read(imagen.getArchivo())); // se crea el valor de las entradas de la red para esta imagen del catalogo
            } catch (IOException ex) {
                Logger.getLogger(RedNeuronal.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            //Asignar los valores de las salidas
            for(int i = 0; i < CatalogoImagenes.size(); i++){
                if(i == imagen.getId()) //asignar un valor de uno al numero de salida correspondiente a esta imagen del catalogo
                    salida.add(i, 1);
                else
                    salida.add(i, 0);
            }
            training.add(img, salida); //AÃ±adir entradas y salidas a la clase encargada del entrenamiento
        }
        System.out.println("Entradas y salidas listas!");
        this.btnCargarImagenes.setEnabled(false);
        
    }//GEN-LAST:event_btnCargarImagenesActionPerformed

    private void btnVerificarPalabraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerificarPalabraActionPerformed
        // TODO add your handling code here:
        try {  
                for (String imagen: palabra)
                {
                    this.DeterminarLetra(imagen);
                }
                //System.out.println("La palabra es: "+this.palabraTotal);
                this.lblMessage.setText("El Codigo de Barra es: "+this.palabraTotal);
                this.TotalCodigosDeBarra.add(this.palabraTotal);
                this.lbxCodigos.setListData(TotalCodigosDeBarra.toArray());
                
                this.palabra.clear();
                this.palabraTotal="";
                
            } catch (IOException ex) 
            {
         
                JOptionPane.showMessageDialog(null, "Debe de Entrenar la red antes de comprobar la imagen!!!");}
    }//GEN-LAST:event_btnVerificarPalabraActionPerformed

    
    
    

    
    private void btnAbrirImagenCodigoDeBarraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAbrirImagenCodigoDeBarraActionPerformed
        // TODO add your handling code here:
        this.lblMessage.setText("");
     int resp;      
     resp=elegirfichero.showOpenDialog(this);
     String rutaImagen;
     if(resp==JFileChooser.APPROVE_OPTION)//Verifica si se selecciono alguna archivo
     {
         rutaImagen=this.elegirfichero.getSelectedFile().toString();//ruta de origen de la imagen
         ImageIcon icon=new ImageIcon(rutaImagen);//      
         Icon icono = new ImageIcon(icon.getImage().getScaledInstance(this.etiImagen.getWidth(), this.etiImagen.getHeight(), Image.SCALE_DEFAULT));
         this.etiImagen.setIcon(icono);//se agrega la imagen a la etiqueta label.
         palabra.add(rutaImagen);//se almacena la direccion en una lista de string
     }
        
    }//GEN-LAST:event_btnAbrirImagenCodigoDeBarraActionPerformed

     public void EntrenarRed()throws IOException{            
        //Entrenar la red neuronal
        System.out.println("Entrenando...");        
        Backpropagation train = new Backpropagation(network, training);        
        train.addStrategy(new ResetStrategy(0.01, 100));        
        //TrainingDialog.trainDialog(train, this.network, this.training);
        EncogUtility.trainConsole(train, this.network, this.training, 1);        
        System.out.println("La informaciÃ³n ha sido asimilada");   
    }    
    
    public void DeterminarLetra(String imagenDir) throws IOException{
            
            ImageMLData img = new ImageMLData(ImageIO.read(new File(imagenDir)));            
            img.downsample(downsample, false, ImgHeight, ImgWidth, 1, 0);            
            int idx = network.winner(img);            
            palabraTotal=this.palabraTotal+this.CatalogoImagenes.get(idx).getNombre();
            
            
           //System.out.println("La letra en la imagen es la '" + this.CatalogoImagenes.get(idx).getNombre() + "'");  
    }

    
    
    
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(RedNeuronal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RedNeuronal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RedNeuronal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RedNeuronal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RedNeuronal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAbrirImagenCodigoDeBarra;
    private javax.swing.JButton btnCargarImagenes;
    private javax.swing.JButton btnEntrenar;
    private javax.swing.JButton btnVerificarPalabra;
    private javax.swing.JFileChooser elegirfichero;
    private javax.swing.JLabel etiImagen;
    private javax.swing.JFileChooser jFileChooser2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblCodigoDeBarra;
    private javax.swing.JLabel lblMensaje;
    private javax.swing.JLabel lblMessage;
    private javax.swing.JLabel lblProducto;
    private javax.swing.JList lbxCodigos;
    // End of variables declaration//GEN-END:variables
}
