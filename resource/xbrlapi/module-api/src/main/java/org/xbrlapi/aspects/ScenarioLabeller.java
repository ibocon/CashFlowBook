package org.xbrlapi.aspects;


import org.xbrlapi.utilities.XBRLException;

/**
 * <p>
 * A labeller for the scenario aspect.  This labeller ignores 
 * link role and resource role values.
 * </p>
 * 
 * @author Geoff Shuetrim (geoff@galexy.net)
 */
public class ScenarioLabeller extends BaseLabeller implements Labeller {

    /**
     * 
     */
    private static final long serialVersionUID = 7314875682855781871L;

    /**
     * @param aspect The aspect to be a labeller for.
     */
    public ScenarioLabeller(Aspect aspect) throws XBRLException {
        super(aspect);
        if (! aspect.getId().equals(ScenarioAspect.ID)) throw new XBRLException("This labeller only works for the aspect: " + ScenarioAspect.ID);
    }
    

    /**
     * @see Labeller#getAspectLabelWithoutFallback(String, String, String)
     */
    @Override
    public String getAspectLabelWithoutFallback(String locale, String resourceRole, String linkRole) {
        return "scenario";
    }

}
