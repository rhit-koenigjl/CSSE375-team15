import pygame
import math
from block import Block

class Grid:
    def __init__(self, width, height, bs) -> None:
        self.width = width
        self.height = height
        self.bs = bs

        self.blocks = []

        for i in range(height):
            row = []
            for j in range(width):
                row.append(Block(j * self.bs, i * self.bs, self.bs, "none"))
            self.blocks.append(row)

    def display(self, screen, image_map, current_block):
        for row in self.blocks:
            for b in row:
                b.display(screen, image_map, current_block)

    def set_type(self, x, y, new_type):
        self.blocks[math.floor(y/self.bs)][math.floor(x/self.bs)].set_type(new_type)

    