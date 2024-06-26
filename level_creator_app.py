import pygame
import sys
import json

from level_creator.app.grid import Grid
from level_creator.app.image_manager import ImageManager
from level_creator.app.block_pallete import BlockPallete
from level_creator.app.level_setup import LevelSetup

config_f = open('level_creator\\app-config.json')
config_data = json.load(config_f)

ls = LevelSetup(config_data['destination'])
level_data = ls.get_level_object()

LEVEL_WIDTH, LEVEL_HEIGHT, BLOCK_SIZE, STREAM = int(level_data['width']), int(level_data['height']), int(level_data['block_size']), level_data['data']

grid = Grid(LEVEL_WIDTH, LEVEL_HEIGHT, BLOCK_SIZE, STREAM)
image_manager = ImageManager("level_creator\\app-config.json")
block_pallete = BlockPallete(list(config_data['assets']))

image_keys = list(image_manager.get_keys())
image_keys.append("none")
image_index = 0

# Initialize Pygame
pygame.init()

# Set up the screen
screen_size = (BLOCK_SIZE * LEVEL_WIDTH + 40, BLOCK_SIZE * LEVEL_HEIGHT)
screen = pygame.display.set_mode(screen_size, pygame.RESIZABLE)
pygame.display.set_caption("Minimal Pygame Starter")

# Set up the clock
clock = pygame.time.Clock()

# Main game loop
running = True
mouse_down = False
while running:
    # Event handling
    for event in pygame.event.get():
        if event.type == pygame.QUIT:
            running = False
        if event.type == pygame.MOUSEBUTTONDOWN:
            mouse_down = True
        if event.type == pygame.MOUSEBUTTONUP:
            mouse_down = False
        if event.type == pygame.KEYDOWN:
            if event.key >= 49 and event.key <= 57:
                block_pallete.set_collection(event.key - 49)
            if event.key == pygame.K_LCTRL:
                block_pallete.iterate_collection()
            if event.key == pygame.K_m:
                ls.dump_data(grid.get_data_string())
                pygame.quit()
                sys.exit()

    # Fill the screen with white
    screen.fill((200, 200, 200))  # RGB values for white

    # Game logic goes here
    mx, my = pygame.mouse.get_pos()
    if mouse_down and mx >= 0 and mx < BLOCK_SIZE * LEVEL_WIDTH >= 0 and my < BLOCK_SIZE * LEVEL_HEIGHT and my > 0:
        grid.set_type(mx, my, block_pallete.get_active_type())

    # Drawing code goes here
    grid.display(screen, image_manager.get_image_map(), block_pallete.get_active_type())
    block_pallete.display(screen, image_manager.get_image_map())

    # Update the display
    pygame.display.flip()

    # Cap the frame rate
    clock.tick(60)

# Quit Pygame and the program
pygame.quit()
sys.exit()