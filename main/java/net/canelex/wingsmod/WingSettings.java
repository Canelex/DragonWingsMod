package net.canelex.wingsmod;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

import java.awt.*;

public class WingSettings
{
	private Configuration config;
	public boolean enabled;
	public boolean colored;
	public boolean chroma;
	public int scale;
	public int hue;

	public WingSettings(Configuration config)
	{
		this.config = config;
	}

	public float[] getColors()
	{
		if (!colored)
		{
			return new float[] {1F, 1F, 1F};
		}

		Color color = Color.getHSBColor(chroma ? (System.currentTimeMillis() % 1000) / 1000F : hue / 100F, 0.8F, 1F);
		return new float[] {color.getRed()/255F, color.getGreen()/255F, color.getBlue()/255F};
	}

	public void saveConfig()
	{
		updateConfig(false);
		config.save();
	}

	public void loadConfig()
	{
		config.load();
		updateConfig(true);
	}

	private void updateConfig(boolean load)
	{
		Property prop;

		prop = config.get("All", "Enabled", true);
		if (load) enabled = prop.getBoolean(); else prop.set(enabled);

		prop = config.get("All", "Colored", false);
		if (load) colored = prop.getBoolean(); else prop.set(colored);

		prop = config.get("All", "Chroma", false);
		if (load) chroma = prop.getBoolean(); else prop.set(chroma);

		prop = config.get("All", "Hue", 100);
		if (load) hue = prop.getInt(); else prop.set(hue);

		prop = config.get("All", "Scale", 100);
		if (load) scale = prop.getInt(); else prop.set(scale);
	}
}
