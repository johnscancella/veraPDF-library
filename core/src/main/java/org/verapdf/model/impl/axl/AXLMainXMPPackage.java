package org.verapdf.model.impl.axl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.verapdf.model.baselayer.Object;
import org.verapdf.model.xmplayer.MainXMPPackage;
import org.verapdf.pdfa.flavours.PDFAFlavour;

import com.adobe.xmp.XMPConst;
import com.adobe.xmp.impl.VeraPDFMeta;
import com.adobe.xmp.impl.VeraPDFXMPNode;

/**
 * Current class is representation of XMPPackage interface from abstract model based on adobe xmp library
 *
 * @author Maksim Bezrukov
 */
public class AXLMainXMPPackage extends AXLXMPPackage implements MainXMPPackage {

    public static final String MAIN_XMP_PACKAGE_TYPE = "MainXMPPackage";

    public static final String IDENTIFICATION = "Identification";

    /**
     * Constructs new object
     *
     * @param xmpMetadata          object that represents this package
     * @param isSerializationValid true if metadata is valid
     */
    public AXLMainXMPPackage(VeraPDFMeta xmpMetadata, boolean isSerializationValid, PDFAFlavour flavour) {
        super(xmpMetadata, isSerializationValid, true, false, null, MAIN_XMP_PACKAGE_TYPE, flavour);
    }

    public AXLMainXMPPackage(VeraPDFMeta xmpMetadata, boolean isSerializationValid, boolean isClosedChoiceCheck, PDFAFlavour flavour) {
        super(xmpMetadata, isSerializationValid, true, isClosedChoiceCheck, null, MAIN_XMP_PACKAGE_TYPE, flavour);
    }

    /**
     * @param link name of the link
     * @return List of all objects with link name
     */
    @Override
    public List<? extends Object> getLinkedObjects(String link) {
        switch (link) {
            case IDENTIFICATION:
                return this.getIdentification();
            default:
                return super.getLinkedObjects(link);
        }
    }

    private List<AXLPDFAIdentification> getIdentification() {
        VeraPDFMeta xmpMetadata = this.getXmpMetadata();
        if (xmpMetadata != null) {
            for (VeraPDFXMPNode node : xmpMetadata.getProperties()) {
                if (XMPConst.NS_PDFA_ID.equals(node.getNamespaceURI())) {
                    List<AXLPDFAIdentification> res = new ArrayList<>(1);
                    res.add(new AXLPDFAIdentification(xmpMetadata));
                    return res;
                }
            }
        }
        return Collections.emptyList();
    }
}
