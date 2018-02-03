package net.canelex.wingsmod.gui;

import net.canelex.wingsmod.WingSettings;
import net.canelex.wingsmod.WingsMod;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.fml.client.config.GuiSlider;

import java.io.IOException;

public class GuiEditWings extends GuiScreen
{
	private WingSettings settings;
	private GuiSlider sliderScale;
	private GuiButton buttonChroma;
	private GuiSlider sliderHue;

	public GuiEditWings(WingsMod mod)
	{
		this.settings = mod.getSettings();
	}

	@Override
	public void initGui()
	{
		buttonList.add(new GuiButton(0, width / 2 - 50, height / 2 - 50, 100, 20, "Wings: " + getColoredBool(settings.enabled)));
		buttonList.add(sliderScale = new GuiSlider(1, width / 2 - 50, height / 2 - 25, 100, 20, "Scale: ", "%", 60, 140, settings.scale, false, true));
		buttonList.add(new GuiButton(2, width / 2 - 50, height / 2, 100, 20, "Color: " + getColoredBool(settings.colored)));
		buttonList.add(sliderHue = new GuiSlider(3, width / 2 - 50, height / 2 + 25, 100, 20, "Hue: ", "%", 0, 100, settings.hue, false, true));
		buttonList.add(buttonChroma = new GuiButton(4, width / 2 - 50, height / 2 + 50, 100, 20, "Chroma: " + getColoredBool(settings.chroma)));
		sliderHue.visible = settings.colored;
		buttonChroma.visible = settings.colored;
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks)
	{
		drawDefaultBackground();

		super.drawScreen(mouseX, mouseY, partialTicks);

		if (settings.chroma)
		{
			sliderHue.setValue((System.currentTimeMillis() % 1000) / 1000F * 100);
			sliderHue.updateSlider();
		}
		else
		{
			settings.hue = sliderHue.getValueInt();
		}

		settings.scale = sliderScale.getValueInt();
	}

	@Override
	public void onGuiClosed()
	{
		settings.saveConfig();
	}

	@Override
	protected void actionPerformed(GuiButton button) throws IOException
	{
		switch (button.id)
		{
		case 0:
			settings.enabled = !settings.enabled;
			button.displayString = "Wings: " + getColoredBool(settings.enabled);
			break;
		case 2:
			settings.colored = !settings.colored;
			button.displayString = "Color: " + getColoredBool(settings.colored);
			sliderHue.visible = settings.colored;
			buttonChroma.visible = settings.colored;
			break;
		case 4:
			settings.chroma = !settings.chroma;
			button.displayString = "Chroma: " + getColoredBool(settings.chroma);
			sliderHue.setValue(settings.hue);
			sliderHue.updateSlider();
			break;
		}
	}

	@Override
	public boolean doesGuiPauseGame()
	{
		return false;
	}

	private String getColoredBool(boolean bool)
	{
		if (bool)
		{
			return EnumChatFormatting.GREEN + "Enabled";
		}

		return EnumChatFormatting.RED + "Disabled";
	}
}
