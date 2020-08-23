package org.xbrlapi.fragment.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.Assert;
import org.testng.AssertJUnit;
import java.util.List;
import java.util.Map;

import org.xbrlapi.Arc;
import org.xbrlapi.ArcEnd;
import org.xbrlapi.DOMLoadingTestCase;
import org.xbrlapi.ExtendedLink;
import org.xbrlapi.Fragment;
import org.xbrlapi.Locator;
import org.xbrlapi.Resource;
import org.xbrlapi.XlinkDocumentation;
import org.xbrlapi.utilities.Constants;
/**
 * Tests the implementation of the org.xbrlapi.ExtendedLink interface.
 * Uses the DOM-based data store to ensure rapid testing.
 * @author Geoffrey Shuetrim (geoff@galexy.net)
 */
public class ExtendedLinkTestCase extends DOMLoadingTestCase {
	private final String STARTING_POINT_A = "test.data.multi.concept.schema";
	private final String STARTING_POINT_B = "test.data.extended.link.documentation.element";
	
	@BeforeMethod
    protected void setUp() throws Exception {
        super.setUp();
		loader.discover(this.getURI(STARTING_POINT_A));		
		loader.discover(this.getURI(STARTING_POINT_B));		
	}

	@AfterMethod
    protected void tearDown() throws Exception {
       super.tearDown();
	}	
	
	/**
	 * Test getting the documentation fragments in the extended link.
	 */
	@Test
    public void testGetDocumentations() {
		try {
			List<Fragment> documentations = store.<Fragment>getXMLResources("XlinkDocumentation");
			for (int i=0; i<documentations.size(); i++) {
				Fragment documentation = documentations.get(0);
				Fragment parent = documentation.getParent();
				if (parent.getDataRootElement().getAttributeNS(Constants.XLinkNamespace.toString(),"type").equals("extended")) {
					ExtendedLink link = (ExtendedLink) parent;
					List<XlinkDocumentation> fragments = link.getDocumentations();
					AssertJUnit.assertEquals(1, fragments.size());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}
	
	/**
	 * Test getting the locators with a given label.
	 */
	@Test
    public void testGetLocatorsByLabel() {
		try {
			List<ExtendedLink> fragments = store.<ExtendedLink>getXMLResources("ExtendedLink");
			ExtendedLink link = fragments.get(0);
			List<Locator> locators = link.getLocatorsWithLabel("summationItem");
			AssertJUnit.assertEquals(1, locators.size());
			locators = link.getLocatorsWithLabel("contributingItem");
			AssertJUnit.assertEquals(2, locators.size());
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}

	/**
	 * Test getting the arc ends with a given label.
	 */
	@Test
    public void testGetArcEndsByLabel() {
		try {
			List<ExtendedLink> fragments = store.<ExtendedLink>getXMLResources("ExtendedLink");
			ExtendedLink link = fragments.get(0);
			List<ArcEnd> arcends = link.getArcEndsWithLabel("summationItem");
			AssertJUnit.assertEquals(1, arcends.size());
			arcends = link.getArcEndsWithLabel("contributingItem");
			AssertJUnit.assertEquals(2, arcends.size());
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}
	
    /**
     * Test getting the arc ends with a given label.
     */
    @Test
    public void testGetArcEndIndicesByLabel() {
        try {
            List<ExtendedLink> links = store.<ExtendedLink>getXMLResources("ExtendedLink");
            ExtendedLink link = links.get(0);
            Map<String,List<String>> map = link.getArcEndIndicesByLabel();
            for (String label: map.keySet()) {
                for (String index: map.get(label)) {
                    logger.info(label + " " + index);
                }
            }
            List<ArcEnd> arcends = link.getArcEndsWithLabel("summationItem");
            AssertJUnit.assertEquals(1, arcends.size());
            arcends = link.getArcEndsWithLabel("contributingItem");
            AssertJUnit.assertEquals(2, arcends.size());
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }	
	
	/**
	 * Test getting the locators with a absolute Href.
	 */
	@Test
    public void testGetLocatorsByHref() {
		try {
			List<ExtendedLink> fragments = store.<ExtendedLink>getXMLResources("ExtendedLink");
			ExtendedLink link = fragments.get(0);
			List<Locator> locators = link.getLocatorsWithHref(configuration.getProperty("test.data.baseURI") + "Common/instance/397-ABC.xsd#B");
			AssertJUnit.assertEquals(1, locators.size());
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}

	/**
	 * Test getting the arcs in the extended link.
	 */
	@Test
    public void testGetArcs() {
		try {
			List<ExtendedLink> fragments = store.<ExtendedLink>getXMLResources("ExtendedLink");
			ExtendedLink link = fragments.get(0);
			List<Arc> arcs = link.getArcs();		
			AssertJUnit.assertEquals(1, arcs.size());
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}
	
	/**
	 * Test getting the arcs with a given from label.
	 */
	@Test
    public void testGetArcsByFromLabel() {
		try {
			List<ExtendedLink> fragments = store.<ExtendedLink>getXMLResources("ExtendedLink");
			ExtendedLink link = fragments.get(0);
			List<Arc> arcs = link.getArcsWithFromLabel("summationItem");
			AssertJUnit.assertEquals(1, arcs.size());
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}

	/**
	 * Test getting the arcs with a given from label.
	 */
	@Test
    public void testGetArcsByToLabel() {
		try {
			List<ExtendedLink> fragments = store.<ExtendedLink>getXMLResources("ExtendedLink");
			ExtendedLink link = fragments.get(0);
			List<Arc> arcs = link.getArcsWithToLabel("contributingItem");
			AssertJUnit.assertEquals(1, arcs.size());
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}	
	
	/**
	 * Test getting the locators in the extended link.
	 */
	@Test
    public void testGetLocators() {
		try {
			List<ExtendedLink> fragments = store.<ExtendedLink>getXMLResources("ExtendedLink");
			ExtendedLink link = fragments.get(0);
			List<Locator> locators = link.getLocators();		
			AssertJUnit.assertEquals(3, locators.size());
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}	
	
	/**
	 * Test getting the arcs in the extended link.
	 */
	@Test
    public void testGetResources() {
		try {
			List<ExtendedLink> fragments = store.<ExtendedLink>getXMLResources("ExtendedLink");
			ExtendedLink link = fragments.get(1);
			List<Resource> resources = link.getResources();		
			AssertJUnit.assertEquals(1, resources.size());
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}
	
	/**
	 * Test getting the arcs with a given from label.
	 */
	@Test
    public void testGetResourcesByLabel() {
		try {
            List<ExtendedLink> links = store.<ExtendedLink>getXMLResources("ExtendedLink");
		    for (ExtendedLink link: links) {
		        List<Resource> resources = link.getResources();
		        for (Resource resource: resources) {
		            AssertJUnit.assertTrue(link.getResourcesWithLabel(resource.getLabel()).size() > 0);
		        }
		    }
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}

    /**
     * Test getting the locators in the extended link.
     */
    @Test
    public void testGetLocatorTargetIndices() {
    	try {
    		List<ExtendedLink> links = store.<ExtendedLink>getXMLResources("ExtendedLink");
    		for (ExtendedLink link: links) {
    		    Map<String,String> map = link.getLocatorTargetIndices();
    		    for (String key: map.keySet()) {
    		        logger.info(key + ": "+ map.get(key));
    		        AssertJUnit.assertTrue(map.get(key).equals(((Locator)store.getXMLResource(key)).getTarget().getIndex()));
    		    }
    		}
    	} catch (Exception e) {
    		e.printStackTrace();
    		Assert.fail(e.getMessage());
    	}
    }	
	
}
