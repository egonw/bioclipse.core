package net.bioclipse.cdk10.sdfeditor.outline;

import net.bioclipse.cdk10.sdfeditor.Activator;
import net.bioclipse.cdk10.sdfeditor.editor.SDFEditor;
import net.bioclipse.cdk10.sdfeditor.editor.StructureTableEntry;

import org.apache.log4j.Logger;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.views.contentoutline.ContentOutlinePage;


public class SDFOutlinePage extends ContentOutlinePage {

//    private static final Logger logger = Logger.getLogger(SDFOutlinePage.class);

    //Image for entries
    Image image2d=Activator.imageDescriptorFromPlugin(
           Activator.PLUGIN_ID, "icons/molecule2D.gif" ).createImage();

    //Store where we come from to get viewer model
    IEditorInput editorInput;
    SDFEditor editor;

    /**
     * Constructor, called from SDFileEditor
     * @param editorInput
     * @param editor
     */
    public SDFOutlinePage(IEditorInput editorInput, SDFEditor editor) {
        this.editor=editor;
        this.editorInput=editorInput;
    }

    /**
     * Set up the Treeviewer
     */
    @Override
    public void createControl( Composite parent ) {
        
        //Creates the TreeViewer
        super.createControl( parent );

        /**
         * The Content provider
         */
        getTreeViewer().setContentProvider(new ITreeContentProvider(){

            StructureTableEntry[] entries;
            public Object[] getChildren( Object parentElement ) {
                return null;
            }

            public Object getParent( Object element ) {
                return entries;
            }

            public boolean hasChildren( Object element ) {
                return false;
            }

            public Object[] getElements( Object inputElement ) {
                if ( inputElement instanceof StructureTableEntry[] ) {
                    entries = (StructureTableEntry[]) inputElement;
                    return entries;
                }
                return new Object[0];
            }

            public void dispose() {
            }

            public void inputChanged( Viewer viewer, Object oldInput,
                                      Object newInput ) {
            }
            
        });

        
        /**
         * The Label provider
         */
        getTreeViewer().setLabelProvider( new ILabelProvider(){

            public Image getImage( Object element ) {
                return image2d;
            }

            public String getText( Object element ) {
                if ( element instanceof StructureTableEntry ) {
                    StructureTableEntry entry = (StructureTableEntry) element;
                    return entry.toString();
                }
                return element.toString();
            }

            public void addListener( ILabelProviderListener listener ) {
            }

            public void dispose() {
            }

            public boolean isLabelProperty( Object element, String property ) {
                return false;
            }

            public void removeListener( ILabelProviderListener listener ) {
            }
        });
        
        getTreeViewer().setInput( editor.getEntries() );
        
    }
    
}
