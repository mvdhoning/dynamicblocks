# Dynamic Blocks Mod

This minecraft mod allows you to add dynamic blocks to the game via the mods config file.

Main useage is for using in [modular machinery](https://minecraft.curseforge.com/projects/modular-machinery) machines. Just add a redstone-comparator to the controller to get a redstone output for its state.

## Resource Loader
With [resource loader](https://www.curseforge.com/minecraft/mc-mods/resource-loader) you can give those blocks a custom look.

Make a dynamicblocksmod folder under the resources folder and add blockstate json files with the names of your added blocks. Be sure you know how to make [resource packs](https://minecraft.gamepedia.com/Tutorials/Creating_a_resource_pack).

dynamic_block.json
with variants redstone you get to choose the texture per redstone level
```json
{
	"forge_marker": 1,
	"defaults": {
		"textures": {
			"all": "dynamicblocksmod:blocks/rsl00"
		}
	},
	"variants": {
		"redstone": {
            "level_00": {
            	"model": "cube_all",
				"textures": {
					"all": "dynamicblocksmod:blocks/rsl00"
				}
            },
            "level_01": {
            	"model": "cube_all",
				"textures": {
					"all": "dynamicblocksmod:blocks/rsl01"
				}
            },
            "level_02": {
            	"model": "cube_all",
				"textures": {
					"all": "dynamicblocksmod:blocks/rsl02"
				}
            },
            "level_03": {
            	"model": "cube_all",
				"textures": {
					"all": "dynamicblocksmod:blocks/rsl03"
				}
            },
            "level_04": {
            	"model": "cube_all",
				"textures": {
					"all": "dynamicblocksmod:blocks/rsl04"
				}
            },
            "level_05": {
            	"model": "cube_all",
				"textures": {
					"all": "dynamicblocksmod:blocks/rsl05"
				}
            },
            "level_06": {
            	"model": "cube_all",
				"textures": {
					"all": "dynamicblocksmod:blocks/rsl06"
				}
            },
            "level_07": {
            	"model": "cube_all",
				"textures": {
					"all": "dynamicblocksmod:blocks/rsl07"
				}
            },
            "level_08": {
            	"model": "cube_all",
				"textures": {
					"all": "dynamicblocksmod:blocks/rsl08"
				}
            },
            "level_09": {
            	"model": "cube_all",
				"textures": {
					"all": "dynamicblocksmod:blocks/rsl09"
				}
            },
            "level_10": {
            	"model": "cube_all",
				"textures": {
					"all": "dynamicblocksmod:blocks/rsl10"
				}
            },
            "level_11": {
            	"model": "cube_all",
				"textures": {
					"all": "dynamicblocksmod:blocks/rsl11"
				}
            },
            "level_12": {
            	"model": "cube_all",
				"textures": {
					"all": "dynamicblocksmod:blocks/rsl12"
				}
            },
            "level_13": {
            	"model": "cube_all",
				"textures": {
					"all": "dynamicblocksmod:blocks/rsl13"
				}
            },
            "level_14": {
            	"model": "cube_all",
				"textures": {
					"all": "dynamicblocksmod:blocks/rsl14"
				}
            },
            "level_15": {
            	"model": "cube_all",
            	"textures": {
					"all": "dynamicblocksmod:blocks/rsl15"
				}
            }      
           },
		"normal": {
			"model": "cube_all"
		},
		"inventory": {
			"model": "cube_all"
		}
	}
}
```